package application.controller;

/*
 * This is the controller class for Templates.fxml view
 *
 * MyUTSA ID: gos049
 * Assignment: Resume Builder Project
 * Class: CS-3443-01T-Summer-2021-Application Programming
 * 
 * @author: Hamza Hamdan
 * 
 */

import application.extras.EnhancedAlert;
import application.pdf.TemplateAPdfGenerator;
import application.pdf.TemplateBPdfGenerator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TemplatesController extends EnhancedAlert{

	/**
	 * JavaFX ImageView object
	 */
	@FXML
	private ImageView templateAImageView, templateBImageView;

	/**
	 * initialize method handles mouse click event for 
	 * both image views and also gives animation to the 
	 * image views when mouse enters or exists the image view
	 */
	@FXML
	public void initialize() {

		templateAImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				TemplateAPdfGenerator generator = new TemplateAPdfGenerator();
				try {
					String resumeFileName = generator.generatePdfFile();
					if(resumeFileName != null) {
						showAlertWindow("Your resume was created successfully: " + resumeFileName, "info", 625, 132);
					}else {
						showAlertWindow("Error while creating your resume. Make sure to fill all fields in all pages.", "error", 625, 132);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR: issue while generating your resume!!");
				}

				event.consume();
			}
		});

		templateAImageView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				templateAImageView.setFitWidth(225);
				templateAImageView.setFitHeight(240);

				event.consume();
			}
		});

		templateAImageView.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				templateAImageView.setFitWidth(200);
				templateAImageView.setFitHeight(215);

				event.consume();
			}
		});

		templateBImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				TemplateBPdfGenerator generator = new TemplateBPdfGenerator();
				try {
					String resumeFileName = generator.generatePdfFile();
					if(resumeFileName != null) {
						showAlertWindow("Your resume was created successfully: " + resumeFileName, "info", 625, 132);
					}else {
						showAlertWindow("Error while creating your resume. Make sure to fill all fields in all pages.", "error", 625, 132);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERROR: issue while generating your resume!!");
				}

				event.consume();
			}
		});

		templateBImageView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				templateBImageView.setFitWidth(225);
				templateBImageView.setFitHeight(240);

				event.consume();
			}
		});

		templateBImageView.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				templateBImageView.setFitWidth(200);
				templateBImageView.setFitHeight(215);

				event.consume();
			}
		});
	}

}
