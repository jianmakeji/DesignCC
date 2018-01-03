package com.cidic.design.service;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.News;
import com.cidic.design.model.NewsPageModel;

public interface NewsService {
	
	public void createNews(News news);
	
	public void deleteNews(int id);
	
	public void updateNews(News news);
	
	public Optional<News> findNewsById(int id);
	
	public NewsPageModel findNewsByPage(int offset, int limit);
	
	public List<News> getTopThreeNews();
}
