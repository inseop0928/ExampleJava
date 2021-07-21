package com.web.crawling.vo;

public class MovieVO {
    private int mno;
    private String title;
    private String genre;
    private String regdate;
    private String director;
    private String actor;
    private String grade;
    private String poster;
    private int like;
    private String story;

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	@Override
	public String toString() {
		return "MovieVO{" +
				"mno=" + mno +
				", title='" + title + '\'' +
				", genre='" + genre + '\'' +
				", regdate='" + regdate + '\'' +
				", director='" + director + '\'' +
				", actor='" + actor + '\'' +
				", grade='" + grade + '\'' +
				", poster='" + poster + '\'' +
				", like=" + like +
				", story='" + story + '\'' +
				'}';
	}
}
