package com.learnk8s.knote.Note;

import org.junit.Test;
import org.junit.Assert.assertEquals;
import org.junit.experimental.categories.Category;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Note_Test {

	@Test
	public void testNormalCaseForToString() {
		Note note = new Note();
		note.setDescription("Normal Description");
		assertEquals("Normal Description", note.toString());
	}

	@Test
	public void testToStringWithNullDescription() {
		Note note = new Note();
		note.setDescription(null);
		assertEquals("null", note.toString());
	}

	@Test
	public void testToStringWithEmptyDescription() {
		Note note = new Note();
		note.setDescription("");
		assertEquals("", note.toString());
	}

	@Test
	public void testToStringWithSpecialCharacters() {
		Note note = new Note();
		note.setDescription("Special Characters: @#$%^&*");
		assertEquals("Special Characters: @#$%^&*", note.toString());
	}

	@Test
	public void testToStringWithLongDescription() {
		Note note = new Note();
		String longDescription = "This is a very long description that goes on and on...";
		note.setDescription(longDescription);
		assertEquals(longDescription, note.toString());
	}

}