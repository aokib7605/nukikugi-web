package com.webApplication.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.webApplication.repository.ImagesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Images {
private final ImagesRepository ir;
	
	public void setImageListModel(Model model) {
		model.addAttribute("imageList", ir.getImageDatas());
	}
	
	public void setImageModel(Model model, String name, Long id){
		model.addAttribute(name, ir.getImageData(id));
	}
	
	public void setPageImageListModel(Model model, String page) {
		model.addAttribute("imageList", ir.getPageImageDatas(page));
	}
	
	public void setPageImageModel(Model model, String name, String page){
		model.addAttribute("image", ir.getPageImageData(name, page));
	}
	
	public com.webApplication.entity.Images getImage(Long id){
		return ir.getImageData(id);
	}
	
	public com.webApplication.entity.Images getImage(String fileName, String page){
		return ir.getPageImageData(fileName, page);
	}
	
	public void uploadImage(MultipartFile file, Model model, String page) {
        try {
            saveImage(file, page);
            model.addAttribute("message", "Uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload: " + e.getMessage());
        }
	}

	private void saveImage(MultipartFile file, String page) throws IOException {
		ir.insertImageData(file.getOriginalFilename(), file.getContentType(), page, file.getBytes());
	}
	
	public void updateImageData(Long id, String fileName, String page) {
		ir.updateImageData(id, fileName, page);
	}
	
	public void deleteImageData(Long id) {
		ir.deleteImageData(id);
	}
}
