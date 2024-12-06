package com.learnk8s.knote.Controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.ui.Model
import org.springframework.http.ResponseEntity
import com.learnk8s.knote.Note.Note
import java.util.List
import org.junit.jupiter.api.Assertions
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.junit.jupiter.api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.multipart.MultipartFile
import com.learnk8s.knote.Repository.NotesRepository
import com.learnk8s.knote.UploadConfig.KnoteProperties
import io.micrometer.core.ipc.http.HttpSender.Response
import java.io.File
import java.util.Collections
import java.util.UUID
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.multipart.MultipartFile
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.HttpClientErrorException.BadRequest
import com.learnk8s.knote.Note.Note
import com.learnk8s.knote.Repository.NotesRepository
import com.learnk8s.knote.UploadConfig.KnoteProperties
import io.micrometer.core.ipc.http.HttpSender.Response
import java.io.File
import java.util.Collections
import java.util.List
import java.util.UUID
import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

public class KnoteController_Test {

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		knoteController = new KnoteController(notesRepository, properties, parser, renderer);
	}

	@Test
	@Tag("valid")
	public void testIndexWithValidDataAndNonEmptyNotesList() {
		List<Note> nonEmptyNotes = new ArrayList<>();
		nonEmptyNotes.add(new Note("1", "Sample Note 1"));
		when(notesRepository.findAll()).thenReturn(nonEmptyNotes);
		ResponseEntity<List<Note>> responseEntity = knoteController.index(new Model());
		assertNotNull(responseEntity);
		assertEquals(nonEmptyNotes, responseEntity.getBody());
	}

	@Test
	@Tag("valid")
	public void testIndexWithValidDataAndEmptyNotesList() {
		List<Note> emptyNotes = new ArrayList<>();
		when(notesRepository.findAll()).thenReturn(emptyNotes);
		ResponseEntity<List<Note>> responseEntity = knoteController.index(new Model());
		assertNotNull(responseEntity);
		assertEquals(emptyNotes, responseEntity.getBody());
	}

@Test
@Tag("valid")
public void testIndexWithValidDataAndNullNotesList() {
    when(notesRepository.findAll()).thenReturn(null);
    ResponseEntity<List<Note>> responseEntity = knoteController.index(new Model());
    assertNotNull(responseEntity);
    assertNull(responseEntity.getBody());
}

@Test
@Tag("valid")
public void testIndexWithExceptionDuringNotesRetrieval() {
    when(notesRepository.findAll()).thenThrow(new RuntimeException("Error retrieving notes"));
    ResponseEntity<List<Note>> responseEntity = knoteController.index(new Model());
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
}

	@Test
	@Tag("valid")
	public void testIndexResponseObjectType() {
		List<Note> notes = new ArrayList<>();
		notes.add(new Note("1", "Sample Note 1"));
		when(notesRepository.findAll()).thenReturn(notes);
		ResponseEntity<List<Note>> responseEntity = knoteController.index(new Model());
		assertNotNull(responseEntity);
		assertTrue(responseEntity.getBody() instanceof List);
		assertTrue(responseEntity.getBody().get(0) instanceof Note);
	}

	@Test
	@DisplayName("Test saveNotes when both parameters are null")
	@Tag("valid")
	public void testSaveNotesWhenBothParametersNull() {
		// Arrange
		MultipartFile file = null;
		String description = "";
		String publish = null;
		String upload = null;
		Model model = new Model();
		// Act
		ResponseEntity<HttpStatusCode> response = KnoteController.saveNotes(file, description, publish, upload, model);
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@DisplayName("Test saveNotes when upload parameter is 'Upload' but file is null")
	@Tag("valid")
	public void testSaveNotesWhenUploadParameterAndNullFile() {
		// Arrange
		MultipartFile file = null;
		String description = "Sample description";
		String publish = null;
		String upload = "Upload";
		Model model = new Model();
		// Act
		ResponseEntity<HttpStatusCode> response = KnoteController.saveNotes(file, description, publish, upload, model);
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@DisplayName("Test saveNotes when upload parameter is 'Upload' and file is empty")
	@Tag("valid")
	public void testSaveNotesWhenUploadParameterAndEmptyFile() {
		// Arrange
		MultipartFile file = new MultipartFile();
		String description = "Sample description";
		String publish = null;
		String upload = "Upload";
		Model model = new Model();
		// Act
		ResponseEntity<HttpStatusCode> response = KnoteController.saveNotes(file, description, publish, upload, model);
		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getBody());
	}

	@Test
	@DisplayName("Test saveNotes when publish parameter is 'Publish'")
	@Tag("valid")
	public void testSaveNotesWhenPublishParameter() {
		// Arrange
		MultipartFile file = null;
		String description = "Sample description";
		String publish = "Publish";
		String upload = null;
		Model model = new Model();
		// Act
		ResponseEntity<HttpStatusCode> response = KnoteController.saveNotes(file, description, publish, upload, model);
		// Assert
		assertEquals(HttpStatus.CREATED, response.getBody());
	}

	@Test
	@DisplayName("Test saveNotes when both parameters are provided")
	@Tag("valid")
	public void testSaveNotesWhenBothParametersProvided() {
		// Arrange
		MultipartFile file = new MultipartFile();
		String description = "Sample description";
		String publish = "Publish";
		String upload = "Upload";
		Model model = new Model();
		// Act
		ResponseEntity<HttpStatusCode> response = KnoteController.saveNotes(file, description, publish, upload, model);
		// Assert
		assertEquals(HttpStatus.CREATED, response.getBody());
	}

}