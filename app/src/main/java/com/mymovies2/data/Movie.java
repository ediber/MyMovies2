package com.mymovies2.data;

import io.realm.RealmObject;

public class Movie extends RealmObject implements IMovieHeadline {
    /**
     * popularity : 18.405
     * vote_count : 2180
     * video : false
     * poster_path : /2CAL2433ZeIihfX1Hb2139CX0pW.jpg
     * id : 19404
     * adult : false
     * backdrop_path : /pVGzV02qmHVoKx9ehBNy7m2u5fs.jpg
     * original_language : hi
     * original_title : दिलवाले दुल्हनिया ले जायेंगे
     * genre_ids : [35,18,10749]
     * title : Dilwale Dulhania Le Jayenge
     * vote_average : 8.9
     * overview : Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.
     * release_date : 1995-10-20
     */

    private double popularity;
    private int vote_count;
    private boolean video;
    private String poster_path;
    private String id;
    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String title;
    private double vote_average;
    private String overview;
    private String release_date;

    private boolean isSelected;

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    @Override
    public boolean getIsSelected() {
        return isSelected;
    }

    @Override
    public void setIsSelected(boolean selected) {
        this.isSelected = selected;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
