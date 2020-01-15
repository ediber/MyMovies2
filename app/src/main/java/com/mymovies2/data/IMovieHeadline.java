package com.mymovies2.data;

public interface IMovieHeadline {
    String getTitle();

    String getPoster_path();

    boolean getIsSelected();

    void setIsSelected(boolean selected);

    String getId();

}
