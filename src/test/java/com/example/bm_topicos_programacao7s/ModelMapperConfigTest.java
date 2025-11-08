package com.example.bm_topicos_programacao7s;

import com.example.bm_topicos_programacao7s.config.ModelMapperConfig;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ModelMapperConfig.class)
class ModelMapperConfigTest {

    @Autowired
    private ModelMapper modelMapper; // bean do Spring

    static class Source {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    static class Dest {
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }

    @Test
    void modelMapperBean_shouldBeCreated() {
        assertNotNull(modelMapper); // garante que o bean foi carregado
    }

    @Test
    void modelMapper_shouldMapPropertiesCorrectly() {
        Source source = new Source();
        source.setName("Willian");

        Dest dest = modelMapper.map(source, Dest.class);

        assertEquals("Willian", dest.getName());
    }
}
