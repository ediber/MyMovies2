package com.mymovies2.data;

import java.util.ArrayList;
import java.util.List;

class Container {

    private List<IMovieHeadline> headlines;

    public Container() {
        this.headlines = new ArrayList<>();
    }

    public void setHeadlines(List<IMovieHeadline> headlines) {
        this.headlines = headlines;
    }

    public void changeSelected(IMovieHeadline headline) {
        for (IMovieHeadline myHeadline : headlines) {
            if(myHeadline.getId().equals(headline.getId())){
                if(myHeadline.getIsSelected()){
                    myHeadline.setIsSelected(false);
                } else { // not selected
                    myHeadline.setIsSelected(true);
                }
            }
        }
    }

    public List<IMovieHeadline> getSelectedMovies() {
        List<IMovieHeadline> selected = new ArrayList<>();

        for (IMovieHeadline headline : headlines)
        if(headline.getIsSelected()){
            selected.add(headline);
        }
        return selected;
    }

    public List<IMovieHeadline> getMoviesHeadlines() {
        return headlines;
    }
}
