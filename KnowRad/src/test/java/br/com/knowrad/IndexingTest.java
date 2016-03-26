package br.com.knowrad;

import br.com.knowrad.dto.DoencaDTO;
import br.com.knowrad.dto.patologia.PatologiaCaseDTO;
import br.com.knowrad.dto.patologia.PatologiaDTO;
import br.com.knowrad.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.RemoteSolrException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IndexingTest {

    List<DoencaDTO> doencas = new ArrayList<DoencaDTO>() {{
        add(new DoencaDTO() {{
            setId(1);
            setNome("tuberculose");
            setPalavras(new String[] {
                    "escavação",
                    "escavada",
                    "nodulos",
                    "intersticiais",
                    "intersticial",
					"sequelas atelectasias"
            });
        }});

        add(new DoencaDTO(){{
            setId(2);
            setNome("asma bronquiectasias");
            setPalavras(new String[] {
                    "mosaico"
            });
        }});

        add(new DoencaDTO(){{
            setId(3);
            setNome("PH");
            setPalavras(new String[] {
                    "mosaico",
                    "consolidações",
					"nods",
					"CL"
            });
        }});

		add(new DoencaDTO(){{
			setId(4);
			setNome("silicose");
			setPalavras(new String[] {
					"nods",
					"nodulos",
					"intersticiais",
					"bandas parenquimatosas"
			});
		}});

		add(new DoencaDTO(){{
			setId(5);
			setNome("pneumocistose");
			setPalavras(new String[] {
					"cistos "
			});
		}});

		add(new DoencaDTO(){{
			setId(6);
			setNome("cancer");
			setPalavras(new String[] {
					"nodulo semisolido"
			});
		}});

		add(new DoencaDTO(){{
			setId(7);
			setNome("pneumonite actínica");
			setPalavras(new String[] {
					"micronodulos"
			});
		}});

		add(new DoencaDTO(){{
			setId(8);
			setNome("esclerodermia");
			setPalavras(new String[] {
					"vidro fosco"
			});
		}});
    }};

	//solr start
	//solr create -c laudos
	//solr status
	//solr stop -all
	/*
	 * Após indexar, ir até o diretório solr-5.4.0\server\solr\laudos\conf e abrir o arquivo managed-schema
	 * localizar os campos indexados (ctrl + F idPaciente, por exemplo) 
	 * e ajustar o tipo de cada um para o singular (longs para log, strings para string, etc)
	 * parar o solr (solr stop -all) e iniciar (solr start).
	 * Realizar nova query e verificar se o JSON retornado está no formato correto.
	 */

	@Test
	@Ignore
	public void readAndIndexPatologies() {

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("casos-patologia.txt");
		List<PatologiaCaseDTO> list = new ArrayList<PatologiaCaseDTO>();

		try {

			String content = IOUtils.toString(inputStream);
			String contents[] = content.split("####INICIOLAUDO####");

			for(int i = 0; i < contents.length; i++) {

				String itens[] = contents[i].split("####SEPARADOR####");

				PatologiaDTO patologia = new PatologiaDTO(Util.verifyString(itens[0]));
				List<PatologiaDTO> listPatologias = new ArrayList<PatologiaDTO>();
				listPatologias.add(patologia);

				PatologiaCaseDTO casoPatologia = new PatologiaCaseDTO();
				casoPatologia.setListPatologiaDTO(listPatologias);
				casoPatologia.setTituloLaudo(Util.verifyString(itens[1]));
				casoPatologia.setModalidadeLaudo("CT");
				casoPatologia.setTextoLaudo(Util.verifyString(itens[2]).replaceAll("\\n", "<br>"));
				list.add(casoPatologia);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * Indexa casos de patologias coletados do arquivo txt
		 */
		String urlString = "http://localhost:8983/solr/patologias";
		SolrClient solr = new HttpSolrClient(urlString);

		try {

			for(PatologiaCaseDTO patologiaCaseDTO : list) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("patologias", patologiaCaseDTO.getListPatologiaDTO().get(0).toString());
				document.addField("tituloLaudo", patologiaCaseDTO.getTituloLaudo());
				document.addField("modalidadeLaudo", patologiaCaseDTO.getModalidadeLaudo());
				document.addField("textoLaudo", patologiaCaseDTO.getTextoLaudo());
				try {
					solr.add(document);
				} catch(RemoteSolrException e) {
				}

				System.out.println("NOVA PATOLOGIA INDEXADA: " + patologiaCaseDTO.getTituloLaudo());
			}
			solr.commit();
			solr.close();
			System.out.println("FIM DA INDEXAÇÃO");

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    List<Long> procurarDoencas(String texto) {

        texto = Util.cleanText(texto);

        List<Long> list = new ArrayList<Long>();

        for(DoencaDTO doenca : doencas) {

            for(String palavra : doenca.getPalavras()) {

                if(texto.indexOf(Util.cleanText(palavra)) > -1) {
                    System.out.println("ACHOU: " + doenca.getNome());
                    list.add(doenca.getId());
                }

            }

        }

        return list;
    }

	@Test
	public void readAndIndexJson() {
		
		/**
		 * Realiza a leitura de um arquivo JSON com os modelos de laudo
		 */
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("laudos.json");
		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		
		try {
			
			String content = IOUtils.toString(inputStream, "UTF-8");

            JsonElement jelement = new JsonParser().parse(content);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("listLaudos");
			
			for(int i = 0; i < jarray.size(); i++) {
				
				JsonObject item = jarray.get(i).getAsJsonObject();

				if(item != null) {
					LaudoDTO dto = new LaudoDTO();
					dto.setIdPaciente(verifyLong(item.get("idpaciente")));
					dto.setNomePaciente(verifyString(item.get("nomepaciente")));
					dto.setTitulo(verifyString(item.get("titulo")));
					dto.setTexto(verifyString(item.get("texto")));
                    dto.setTextoLimpo(Util.cleanText(verifyString(item.get("texto"))));
					dto.setModalidade(verifyString(item.get("modalidade")));
                    dto.setDoencas(procurarDoencas(dto.getTextoLimpo()));
					listLaudo.add(dto);
				}

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * Indexa laudos coletados do arquivo json
		 */
		String urlString = "http://localhost:8983/solr/laudos";
		SolrClient solr = new HttpSolrClient(urlString);
		
		try {
			
			for(LaudoDTO laudoDTO : listLaudo) {
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id_paciente", laudoDTO.getIdPaciente());
				document.addField("nome_paciente", laudoDTO.getNomePaciente());
				document.addField("titulo", laudoDTO.getTitulo());
				document.addField("texto", laudoDTO.getTexto());
                document.addField("texto_limpo", laudoDTO.getTextoLimpo());
				document.addField("modalidade", laudoDTO.getModalidade());
                document.addField("doencas", laudoDTO.getDoencas());
				try {
					solr.add(document);
				} catch(RemoteSolrException e) {
				}
				
				System.out.println("NOVO LAUDO INDEXADO: " + laudoDTO.getIdPaciente());
			}
			solr.commit();
			solr.close();
			System.out.println("FIM DA INDEXAÇÃO");
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void indexDataTest() {
		
		String urlString = "http://localhost:8983/solr/techproducts";
		SolrClient solr = new HttpSolrClient(urlString);

		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "552199");
		document.addField("name", "Gouda cheese wheel");
		document.addField("price", "49.99");
		
		try {
			
//			UpdateResponse response = solr.add(document);
			solr.add(document);
			solr.commit();
			solr.close();
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void queryDataTest() {
		
		String urlString = "http://localhost:8983/solr/techproducts";
		SolrClient solr = new HttpSolrClient(urlString);
		
		SolrQuery query = new SolrQuery();
		query.setQuery("id:552199");
		
		try {
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			String teste = "";
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void readJsonFile() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("laudos.json");
		
		try {
			
			String content = IOUtils.toString(inputStream);
			
			JsonElement jelement = new JsonParser().parse(content);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("listLaudos");
			
			List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
			
			for(int i = 0; i < jarray.size(); i++) {
				
				JsonObject item = jarray.get(i).getAsJsonObject();

				if(item != null) {
					LaudoDTO dto = new LaudoDTO();
					dto.setIdPaciente(verifyLong(item.get("idpaciente")));
					dto.setNomePaciente(verifyString(item.get("nomepaciente")));
					dto.setTitulo(verifyString(item.get("titulo")));
					dto.setTexto(verifyString(item.get("texto")));
					dto.setModalidade(verifyString(item.get("modalidade")));
					listLaudo.add(dto);
				}

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private Long verifyLong(JsonElement jelement) {
		if(jelement == null)
			return new Long(0);
		
		try {
			return jelement.getAsLong();
		} catch(UnsupportedOperationException e) {
			return new Long(0);
		}
	}
	
	private String verifyString(JsonElement jelement) {
		if(jelement == null)
			return "";

		try {
			return jelement.getAsString();
		} catch(UnsupportedOperationException e) {
			return "";
		}
	}
	
	class LaudoDTO {
		
		private String titulo;
		private String texto;
        private String textoLimpo;
		private String nomePaciente;
		private String modalidade;
		private Long idPaciente;
        private List<Long> doencas;
		
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getTexto() {
			return texto;
		}
		public void setTexto(String texto) {
			this.texto = texto;
		}

        public String getTextoLimpo() {
            return textoLimpo;
        }

        public void setTextoLimpo(String textoLimpo) {
            this.textoLimpo = textoLimpo;
        }

        public String getNomePaciente() {
			return nomePaciente;
		}
		public void setNomePaciente(String nomePaciente) {
			this.nomePaciente = nomePaciente;
		}
		public Long getIdPaciente() {
			return idPaciente;
		}
		public void setIdPaciente(Long idPaciente) {
			this.idPaciente = idPaciente;
		}
		public String getModalidade() {
			return modalidade;
		}
		public void setModalidade(String modalidade) {
			this.modalidade = modalidade;
		}

        public List<Long> getDoencas() {
            return doencas;
        }

        public void setDoencas(List<Long> doencas) {
            this.doencas = doencas;
        }
    }
	
}
