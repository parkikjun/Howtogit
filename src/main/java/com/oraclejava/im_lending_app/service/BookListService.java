package com.oraclejava.im_lending_app.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.im_lending_app.controller.UploadBookListForm;
import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dto.LendingBook;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
@Transactional(readOnly = true)
public class BookListService {
	
	@Autowired
	private LendingBookRepository lendingBookRepository;
	
	@Transactional
	public void saveBookListCsvFile(UploadBookListForm uploadBookListForm) {
		List<BookListCSVRecord> bookListCSVRecordList = null;
		
		try (
			InputStream is = uploadBookListForm.getFileupload().getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "EUC-KR");	
		) {
			BeanListProcessor<BookListCSVRecord> rowProcessor = 
					new BeanListProcessor<>(BookListCSVRecord.class);
			CsvParserSettings settings = new CsvParserSettings();
			settings.setHeaderExtractionEnabled(true);
			settings.setProcessor(rowProcessor);
			CsvParser parser = new CsvParser(settings);
			parser.parse(isr);
			
			bookListCSVRecordList = rowProcessor.getBeans();
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Lending_book에 저장
		LendingBook lendingBook;
		for (BookListCSVRecord bookListCSVRecord : bookListCSVRecordList) {
			lendingBook = new LendingBook();
			lendingBook.setIsbn(bookListCSVRecord.getIsbn());
			lendingBook.setBook_name(bookListCSVRecord.getBookName());
			lendingBookRepository.save(lendingBook);
		}
	}
}







