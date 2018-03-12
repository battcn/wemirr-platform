package com.battcn.management.webmagic.entity;

import java.util.Date;

public class Director {
    private Integer id;

    private String directorname;
    
    private String movies;

    private String movieids;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovies() {
		return movies;
	}

	public void setMovies(String movies) {
		this.movies = movies;
	}

	public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(String directorname) {
        this.directorname = directorname == null ? null : directorname.trim();
    }

    public String getMovieids() {
        return movieids;
    }

    public void setMovieids(String movieids) {
        this.movieids = movieids == null ? null : movieids.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}