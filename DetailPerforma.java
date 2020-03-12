package com.project.propensib8.rest;

import com.project.propensib8.model.KomplainModel;
import com.project.propensib8.model.ReviewModel;
import com.project.propensib8.model.UnitParameterModel;
import java.util.List;

public class DetailPerforma {
    int review;
    int komplain;
    int komplainSolved;
    List<ReviewRest> reviewRest;
    List<KomplainRest> komplainRest;
    
    

    public int getKomplainSolved() {
		return komplainSolved;
	}

	public void setKomplainSolved(int komplainSolved) {
		this.komplainSolved = komplainSolved;
	}

	public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getKomplain() {
        return komplain;
    }

    public void setKomplain(int komplain) {
        this.komplain = komplain;
    }

    public List<ReviewRest> getReviewRest() { return reviewRest; }

    public void setReviewRest(List<ReviewRest> reviewRest) { this.reviewRest = reviewRest; }

    public List<KomplainRest> getKomplainRest() { return komplainRest; }

    public void setKomplainRest(List<KomplainRest> komplainRest) { this.komplainRest = komplainRest; }


}
