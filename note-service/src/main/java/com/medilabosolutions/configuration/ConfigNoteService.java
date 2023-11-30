package com.medilabosolutions.configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medilabosolutions.dto.NoteDto;
import com.medilabosolutions.model.Note;

@Configuration
public class ConfigNoteService {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.addMappings(noteMap);
        return mm;
    }

    @Bean
    public Converter<String, LocalDateTime> stringToLocalDateTime() {
        return context -> LocalDateTime.parse(context.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    PropertyMap<NoteDto, Note> noteMap = new PropertyMap<NoteDto, Note>() {
        @Override
        protected void configure() {
            using(stringToLocalDateTime()).map(source.getDate(), destination.getDate());
        }

    };

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}