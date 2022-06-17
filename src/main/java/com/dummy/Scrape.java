package com.dummy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dummy.dao.PostPreview;
import com.dummy.entity.Comment;
import com.dummy.entity.Post;
import com.dummy.entity.Users;
import com.dummy.entity.UserPreview;
import com.dummy.service.CommentService;
import com.dummy.service.PostService;
import com.dummy.service.UserPreviewService;
import com.dummy.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class Scrape {
	
	private static final Logger logger = LogManager.getLogger(Scrape.class);

	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@Autowired
	UserPreviewService userPreviewService;

	@Autowired
	CommentService commentService;

	@Value("${dummy.api}")
	private String dummyURL;

	@Value("${dummy.api.data.limit}")
	private int dataLimit=10;

	public  void addData(ResponseEntity<String> response, RestTemplate restTemplate) {

		JSONObject jsonn=new JSONObject(response.getBody());

		JSONArray array=jsonn.getJSONArray("data");

		try {
			ArrayList<UserPreview> list =new Gson().fromJson(array.toString(), new TypeToken<ArrayList<UserPreview>>() {}.getType());

			List<UserPreview> checkList=userPreviewService.getUserPreviews();
			List<Users> checkListUsers=userService.getUsers();

			CompletableFuture.runAsync(() -> { 
				
				
				list.stream().parallel().forEach(userPreview->{

					String userId=userPreview.getId();

					boolean flag=false;
					for(UserPreview u:checkList) {
						
						if(u.toString().equals(userPreview.toString())) {
							flag=true;
							break;
						}
					}

					if(!flag) {
						userPreviewService.save(userPreview);
						logger.info("save  userPreview....."+userPreview);
					}
					try {
						ResponseEntity<Users> getSingleUser = restTemplate.exchange(dummyURL+"user/"+userId, HttpMethod.GET,null, Users.class);

						if(getSingleUser.getStatusCodeValue()==200) {

							Users user=getSingleUser.getBody();

							boolean userflag=false;
							for(Users u:checkListUsers) {
								if(u.toString().equals(user.toString())) {
									userflag=true;
									break;
								}
							}

							if(!userflag) {
								userService.save(getSingleUser.getBody());	
								logger.info("save  user....."+getSingleUser.getBody());
							}
						}

					}catch (Exception e) {
						e.printStackTrace();
						
						logger.error(e.getStackTrace());
					}

					try {
						ResponseEntity<String> getPost = restTemplate.exchange(dummyURL+"user/"+userId+"/post", HttpMethod.GET,null, String.class);

						if(getPost.getStatusCodeValue()==200) {
							JSONArray arrayobject=new JSONObject(getPost.getBody()).getJSONArray("data");

							try {
								ArrayList<PostPreview> postPreviewlist =new Gson().fromJson(arrayobject.toString(), new TypeToken<ArrayList<PostPreview>>() {}.getType());

								postPreviewlist.stream().parallel().forEach(postPreview->{
									try {
										ResponseEntity<Post> getSinglePost = restTemplate.exchange("https://dummyapi.io/data/v1/post/"+postPreview.getId(), HttpMethod.GET,null, Post.class);
										if(getSinglePost!=null && getSinglePost.getStatusCode().equals(HttpStatus.OK)) {

											Post post=getSinglePost.getBody();
											
											List<Post> posts=postService.getPostByOwner(userPreview);
										
											boolean postflag=false;
											for(Post u:posts) {
												if(u.toString().equals(post.toString())) {
													postflag=true;
													break;
												}
											}

											if(!postflag) {
												
												postService.save(post);
												
												logger.info("save  post....."+post);
											}

										}
									}catch (Exception e) {
										e.printStackTrace();
										
										logger.error(e.getStackTrace());
									}
								});

							} catch (JSONException e) {
								e.printStackTrace();
								
								logger.error(e.getStackTrace());
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
						
						logger.error(e.getStackTrace());
					}

					//---------------------------------------------------------
					ResponseEntity<String> getComments = restTemplate.exchange(dummyURL+"user/"+userId+"/comment", HttpMethod.GET,null, String.class);

					if(getComments.getStatusCodeValue()==200) {

						JSONArray arraygetComments=new JSONObject(getComments.getBody()).getJSONArray("data");

						try {
							ArrayList<Comment> listgetComments =new Gson().fromJson(arraygetComments.toString(), new TypeToken<ArrayList<Comment>>() {}.getType());

							listgetComments.stream().parallel().forEach(comment->{

								List<Comment> commentList=commentService.getPostByOwner(comment.getOwner());
								
								
								boolean commentflag=false;
								for(Comment u:commentList) {
									if(u.toString().equals(comment.toString())) {
										commentflag=true;
										break;
									}
								}

								if(!commentflag) {
									commentService.save(comment);
									
									logger.info("save  comment....."+comment);
									
								}
							});

						} catch (JSONException e) {
							e.printStackTrace();
							
							logger.error(e.getStackTrace());
						}
					}

				});
			}, ForkJoinPool.commonPool());

		} catch (JSONException e) {
			e.printStackTrace();
			
			logger.error(e.getStackTrace());
		}
	}

	public  void startScrapping() {

		RestTemplate restTemplate=new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new HeaderRequestInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
		interceptors.add(new HeaderRequestInterceptor("app-id", "62a982c69ed3c88b4689b8b6"));
		restTemplate.setInterceptors(interceptors);

		ResponseEntity<String> response = restTemplate.exchange(dummyURL+"user?page=1&limit="+dataLimit, HttpMethod.GET,null, String.class);

		if(response.getStatusCodeValue()==200) {

			this.addData(response,restTemplate);

			JSONObject jsonn=new JSONObject(response.getBody());

			int total=jsonn.getInt("total");
			int limit=jsonn.getInt("limit");

			int loop=total/limit;

			for(int i=2;i<=loop;i++) {

				try {
					response = restTemplate.exchange(dummyURL+"user?page="+i+"&limit="+dataLimit, HttpMethod.GET,null, String.class);
					if(response.getStatusCodeValue()==200) {
						this.addData(response,restTemplate);
					}
				}catch (Exception e) {
					e.printStackTrace();
					
					logger.error(e.getStackTrace());
				}
			}

		}
	}
}
