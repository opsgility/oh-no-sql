package com.sample.contosojobs.ContosoJobsApplication.Entity;

public class Resume {

    private String Id;

    private String UserId;

    private String Content;

    private String Key;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	@Override
	public String toString() {
		return "Resume [Id=" + Id + ", UserId=" + UserId + ", Content=" + Content + ", Key=" + Key + "]";
	}
    
    
}
