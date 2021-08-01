package application.controller;

import application.extras.EnhancedAlert;
import application.pdf.TemplateAPdfGenerator;
import application.pdf.TemplateBPdfGenerator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TemplatesController extends EnhancedAlert{

	@FXML
	private ImageView templateAImageView, templateBImageView;

	@FXML
	public void initialize() {

		templateAImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				TemplateAPdfGenerator generator = new TemplateAPdfGenerator();
				try {
					String resumeFileName = generator.generatePdfFile();
					showAlertWindow("Your resume was created successfully: " + resumeFileName,625, 132);
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
					showAlertWindow("Your resume was created successfully: " + resumeFileName,625, 132);
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
