package com.webApplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.webApplication.entity.Movie;
import com.webApplication.entity.Stage;
import com.webApplication.repository.MovieRepository;
import com.webApplication.repository.StageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Special {
	private final StageRepository sr;
	private final MovieRepository mr;
	
	public void setStageListModel(Model model) {
		int count = sr.getStageList().size();
		ArrayList<ArrayList<Stage>> stageSetList = new ArrayList<ArrayList<Stage>>();
		for(int i = 0; i < count; i+=3) {
				if(sr.getStageListByOffset(i) != null) {
					ArrayList<Stage> stageList = sr.getStageListByOffset(i);
					stageSetList.add(stageList);
			}
		}
		model.addAttribute("stageSetList", stageSetList);
	}
	
	public void setMovieListModel(Model model) {
		int count = mr.getMovieList().size();
		ArrayList<ArrayList<Movie>> movieSetList = new ArrayList<ArrayList<Movie>>();
		for(int i = 0; i < count; i+=3) {
				if(mr.getMovieListByOffset(i) != null) {
					ArrayList<Movie> movieList = mr.getMovieListByOffset(i);
					movieSetList.add(movieList);
			}
		}
		model.addAttribute("movieSetList", movieSetList);
	}
	
	public void setMovieModel(Model model, int id) {
		Movie movie = mr.getMovie(id);
		model.addAttribute("movie", movie);
	}
	
	public void setStageModel(Model model, int id) {
		Stage stage = sr.getStage(id);
		model.addAttribute("stage", stage);
	}
	
	public void insertMovie(Model model, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support, String link) {
		mr.insertMovie(stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link);
	}
	
	public void updateMovie(Model model, int id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support, String link) {
		mr.updateMovie(id, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support, link);
	}
	
	public void deleteMovie(Model model, int id) {
		mr.deleteMovie(id);
	}
	
	public void insertStage(Model model, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support) {
		sr.insertStage(stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support);
	}
	
	public void updateStage(Model model, int id, String stage, String title, String value, String date, String place, String mainStaff, String cast, String staff, String story, String ticket, String reservation, String support) {
		sr.updateStage(id, stage, title, value, date, place, mainStaff, cast, staff, story, ticket, reservation, support);
	}
	
	public void deleteStage(Model model, int id) {
		sr.deleteStage(id);
	}
}
