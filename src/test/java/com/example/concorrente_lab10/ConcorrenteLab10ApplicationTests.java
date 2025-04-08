package com.example.concorrente_lab10;

import com.example.concorrente_lab10.models.Dto.ProdutoCompraDto;
import com.example.concorrente_lab10.models.Dto.ProdutoPostDto;
import com.example.concorrente_lab10.models.Produto;
import com.example.concorrente_lab10.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes: Concorrente vs Serial")
class ConcorrenteLab10ApplicationTests {

	private static final int NUMERO_THREADS = 50;

	@Autowired
	private MockMvc driver;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProdutoRepository produtoRepository;

	@BeforeEach
	void setUp() throws Exception {
		ProdutoPostDto produtoPostDto = new ProdutoPostDto("1", "Elden Ring", 229.90, 100);
		Produto produto = new Produto(produtoPostDto);
		this.produtoRepository.salvaProduto(produto);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.produtoRepository = new ProdutoRepository();
	}

	@Nested
	@DisplayName("Apenas para comparar a diferença de velocidade entre operacoes read concorrentes e seriais.")
	class ambienteRead {

		/*
		 * Roda um ambiente de testes que roda várias requisições de leitura de forma concorrente.
		 */
		@Test
		void concorrenteAmbienteRead() throws Exception {
			long inicio = System.nanoTime();

			ExecutorService executor = Executors.newFixedThreadPool(NUMERO_THREADS);

			for(int i = 0; i < NUMERO_THREADS; i++) {
				executor.execute(() -> {
					try {
						ProdutoCompraDto produtoCompraDto = new ProdutoCompraDto("1", 1);
						driver.perform(post("/purchase")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(produtoCompraDto)))
								.andExpect(status().isOk());
					} catch (Exception e) {}
				});
			}
			executor.shutdown();
			executor.awaitTermination(2, TimeUnit.MINUTES);

			long fim = System.nanoTime();
			long duracaoMs = (fim - inicio) / 1_000_000; // converte para milissegundos

			System.out.println("Tempo de execução (concorrente): " + duracaoMs + " ms");

		}

		/*
		 * Roda um ambiente de testes que roda várias requisições de leitura de forma serial.
		 */
		@Test
		void serialAmbienteRead() throws Exception {
			long inicio = System.nanoTime();

			for(int i = 0; i < NUMERO_THREADS; i++) {

				ProdutoCompraDto produtoCompraDto = new ProdutoCompraDto("1", 1);
				driver.perform(post("/purchase")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(produtoCompraDto)))
						.andExpect(status().isOk());
			}

			long fim = System.nanoTime();
			long duracaoMs = (fim - inicio) / 1_000_000; // converte para milissegundos

			System.out.println("Tempo de execução (serial): " + duracaoMs + " ms");

		}

	}

	@Nested
	@DisplayName("Testes de integridade junto às requisições")
	class ambienteIntegridade {

		/*
		 * Roda um ambiente de testes que roda várias requisições de escrita de forma concorrente.
		 */
		@Test
		void concorrenteAmbienteWrite() throws Exception {
			long inicio = System.nanoTime();
			ExecutorService executor = Executors.newFixedThreadPool(NUMERO_THREADS);

			for(int i = 0; i < NUMERO_THREADS; i++) {
				executor.execute(() -> {
					try {
						ProdutoCompraDto produtoCompraDto = new ProdutoCompraDto("1", 1);
						driver.perform(post("/purchase")
										.contentType(MediaType.APPLICATION_JSON)
										.content(objectMapper.writeValueAsString(produtoCompraDto)))
								.andExpect(status().isOk());
					} catch (Exception e) {}
				});
			}
			executor.shutdown();
			executor.awaitTermination(2, TimeUnit.MINUTES);
			
			long fim = System.nanoTime();
			long duracaoMs = (fim - inicio) / 1_000_000;
			System.out.println("Tempo de execução (concorrente-integridade): " + duracaoMs + " ms");

			assertEquals(50, produtoRepository.getProduto("1").getCountSold().intValue());

		}

		/*
		 * Roda um ambiente de testes que roda várias requisições de escrita de forma serial.
		 */
		@Test
		void serialEscritaWrite() throws Exception {
			
			long inicio = System.nanoTime();

			for(int i = 0; i < NUMERO_THREADS; i++) {

				ProdutoCompraDto produtoCompraDto = new ProdutoCompraDto("1", 1);
				driver.perform(post("/purchase")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(produtoCompraDto)))
					.andExpect(status().isOk());
			}

			long fim = System.nanoTime();
			long duracaoMs = (fim - inicio) / 1_000_000; // converte para milissegundos

			System.out.println("Tempo de execução (serial-integridade): " + duracaoMs + " ms");

			assertEquals(50, produtoRepository.getProduto("1").getCountSold().intValue());
		}

	}

}
