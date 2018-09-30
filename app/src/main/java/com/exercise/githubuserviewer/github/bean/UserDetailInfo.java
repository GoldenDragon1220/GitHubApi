package com.exercise.githubuserviewer.github.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rexhuang on 2018/9/28.
 */

public class UserDetailInfo {

    /**
     * login : defunkt
     * id : 2
     * node_id : MDQ6VXNlcjI=
     * avatar_url : https://avatars0.githubusercontent.com/u/2?v=4
     * gravatar_id :
     * url : https://api.github.com/users/defunkt
     * html_url : https://github.com/defunkt
     * followers_url : https://api.github.com/users/defunkt/followers
     * following_url : https://api.github.com/users/defunkt/following{/other_user}
     * gists_url : https://api.github.com/users/defunkt/gists{/gist_id}
     * starred_url : https://api.github.com/users/defunkt/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/defunkt/subscriptions
     * organizations_url : https://api.github.com/users/defunkt/orgs
     * repos_url : https://api.github.com/users/defunkt/repos
     * events_url : https://api.github.com/users/defunkt/events{/privacy}
     * received_events_url : https://api.github.com/users/defunkt/received_events
     * type : User
     * site_admin : true
     * name : Chris Wanstrath
     * company : @github
     * blog : http://chriswanstrath.com/
     * location : San Francisco
     * email : null
     * hireable : null
     * bio : 🍔
     * public_repos : 107
     * public_gists : 273
     * followers : 20443
     * following : 210
     * created_at : 2007-10-20T05:24:19Z
     * updated_at : 2018-08-15T02:05:37Z
     */

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("node_id")
    @Expose
    private String node_id;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    @SerializedName("gravatar_id")
    @Expose
    private String gravatar_id;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("html_url")
    @Expose
    private String html_url;

    @SerializedName("followers_url")
    @Expose
    private String followers_url;

    @SerializedName("following_url")
    @Expose
    private String following_url;

    @SerializedName("gists_url")
    @Expose
    private String gists_url;

    @SerializedName("starred_url")
    @Expose
    private String starred_url;

    @SerializedName("subscriptions_url")
    @Expose
    private String subscriptions_url;

    @SerializedName("organizations_url")
    @Expose
    private String organizations_url;

    @SerializedName("repos_url")
    @Expose
    private String repos_url;

    @SerializedName("events_url")
    @Expose
    private String events_url;

    @SerializedName("received_events_url")
    @Expose
    private String received_events_url;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("site_admin")
    @Expose
    private boolean site_admin;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("blog")
    @Expose
    private String blog;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("email")
    @Expose
    private Object email;

    @SerializedName("hireable")
    @Expose
    private Object hireable;

    @SerializedName("bio")
    @Expose
    private String bio;

    @SerializedName("public_repos")
    @Expose
    private int public_repos;

    @SerializedName("public_gists")
    @Expose
    private int public_gists;

    @SerializedName("followers")
    @Expose
    private int followers;

    @SerializedName("following")
    @Expose
    private int following;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getHireable() {
        return hireable;
    }

    public void setHireable(Object hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
