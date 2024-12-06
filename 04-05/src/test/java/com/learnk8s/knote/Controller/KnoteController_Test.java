package com.learnk8s.knote.Controller;

import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import com.learnk8s.knote.Note.Note;
import com.learnk8s.knote.Repository.NotesRepository;
import com.learnk8s.knote.UploadConfig.KnoteProperties;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.multipart.MultipartFile;
import io.micrometer.core.ipc.http.HttpSender.Response;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.Assert.assertEquals;

public class KnoteController_Test {

	@Test
	@Category(Categories.valid.class)
	public void testIndexWithValidDataAndNonEmptyNotesList() {
		Model model = new Model();
		List<Note> nonEmptyNotes = new ArrayList<>();
		nonEmptyNotes.add(new Note("1", "Sample Note 1"));
		nonEmptyNotes.add(new Note("2", "Sample Note 2"));
		when(notesRepository.findAll()).thenReturn(nonEmptyNotes);
		ResponseEntity<List<Note>> response = new KnoteController().index(model);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(nonEmptyNotes, response.getBody());
	}

	@Test
	@Category(Categories.valid.class)
	public void testIndexWithValidDataAndEmptyNotesList() {
		Model model = new Model();
		List<Note> emptyNotes = Collections.emptyList();
		when(notesRepository.findAll()).thenReturn(emptyNotes);
		ResponseEntity<List<Note>> response = new KnoteController().index(model);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(emptyNotes, response.getBody());
	}

	@Test
	@Category(Categories.valid.class)
	public void testIndexWithValidDataAndNullNotesList() {
		Model model = new Model();
		when(notesRepository.findAll()).thenReturn(null);
		ResponseEntity<List<Note>> response = new KnoteController().index(model);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	@Category(Categories.valid.class)
	public void testIndexWithExceptionDuringNotesRetrieval() {
		Model model = new Model();
		when(notesRepository.findAll()).thenThrow(new RuntimeException("Exception during retrieval"));
		ResponseEntity<List<Note>> response = new KnoteController().index(model);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody());
	}

	@Test
	@Category(Categories.valid.class)
	public void testIndexResponseObjectType() {
		Model model = new Model();
		List<Note> nonEmptyNotes = new ArrayList<>();
		nonEmptyNotes.add(new Note("1", "Sample Note 1"));
		when(notesRepository.findAll()).thenReturn(nonEmptyNotes);
		ResponseEntity<List<Note>> response = new KnoteController().index(model);
		assertEquals(List.class, response.getBody().getClass());
	}

	@Test
	@Category(Categories.invalid.class)
	public void testSaveNotesWhenBothParametersNull() {
		// Arrange
		KnoteController controller = new KnoteController();
		// Act
		ResponseEntity<HttpStatusCode> response = controller.saveNotes(null, null, null, null, new Model());
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@Category(Categories.invalid.class)
	public void testSaveNotesWhenUploadParameterAndNullFile() {
		// Arrange
		KnoteController controller = new KnoteController();
		// Act
		ResponseEntity<HttpStatusCode> response = controller.saveNotes(null, "Description", null, "Upload",
				new Model());
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@Category(Categories.invalid.class)
	public void testSaveNotesWhenUploadParameterAndEmptyFile() {
		// Arrange
		KnoteController controller = new KnoteController();
		MultipartFile emptyFile = new MultipartFile() {
		};
		// Act
		ResponseEntity<HttpStatusCode> response = controller.saveNotes(emptyFile, "Description", null, "Upload",
				new Model());
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@Category(Categories.valid.class)
	public void testSaveNotesWhenPublishParameter() {
		// Arrange
		KnoteController controller = new KnoteController();
		// Act
		ResponseEntity<HttpStatusCode> response = controller.saveNotes(null, "Description", "Publish", null,
				new Model());
		// Assert - Need to mock saveNote method and verify the behavior
	}

	@Test
	@Category(Categories.valid.class)
	public void testSaveNotesWhenBothParametersProvided() {
		// Arrange
		KnoteController controller = new KnoteController();
		// Act
		ResponseEntity<HttpStatusCode> response = controller.saveNotes(null, "Description", "Publish", "Upload",
				new Model());
		// Assert - Need to mock uploadImage and saveNote methods and verify the behavior
	}

}