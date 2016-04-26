package br.com.knowrad.util;

import br.com.knowrad.dto.doenca.DoencaDTO;
import com.google.gson.JsonElement;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Util {

	public static Boolean verifyList(List list) {
		return list != null && !list.isEmpty();
    }

	public static List<Long> objectToArrayListLong(Object val) {

		if(val == null) {
			return new ArrayList<Long>();
		}

		try {
			return (ArrayList<Long>) val;
		} catch(Exception e) {
			return new ArrayList<Long>();
		}
	}

	public static Long verifyLong(Object val) {
		try {
			return Long.valueOf(String.valueOf(val));
		} catch(Exception e) {
			return new Long(0);
		}
	}
	
	public static String verifyString(Object val) {
		try {

			if(val == null || val.toString().equals("null"))
				return "";

			return String.valueOf(val);
		} catch(Exception e) {
			return "";
		}
	}

	public static String cleanText(String string) {
		if(string == null)
			return "";

		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		string = string.toLowerCase();

		return string;
	}

	public static Long verifyLongJson(JsonElement jelement) {
		if(jelement == null)
			return new Long(0);

		try {
			return jelement.getAsLong();
		} catch(UnsupportedOperationException e) {
			return new Long(0);
		}
	}

	public static String verifyStringJson(JsonElement jelement) {
		if(jelement == null)
			return "";

		try {
			return jelement.getAsString();
		} catch(UnsupportedOperationException e) {
			return "";
		}
	}

	public static List<DoencaDTO> getDoencas() {
		return new ArrayList<DoencaDTO>() {{
			add(new DoencaDTO() {{
				setId(1);
				setNome("Tuberculose");
				setPalavras(new ArrayList<String>() {{
					add("escavação");
					add("escavada");
					add("intersticiais");
					add("intersticial");
					add("nodulos intersticiais");
					add("sequelas atelectasias");
					add("disseminação canalicular");
					add("atelectasia");
					add("obstrução bronq");
					add("sequelas atelectasias");
					add("tuberculose");
				}});
				setSelected(Boolean.FALSE);
				setCytoscape_alias_list(new String[]{"Tuberculose"});
				setCanonicalName("Tuberculose");
				setSUID("1");
				setNodeType("RedWine");
				setName("Tuberculose");
				setShared_name("Tuberculose");
				setNodeTypeFormatted("RedWine");
			}});

//        add(new DoencaDTO(){{
//            setId(2);
//            setNome("asma bronquiectasias");
//            setPalavras(new String[] {
//                    "mosaico"
//            });
//        }});
//
			add(new DoencaDTO(){{
				setId(2);
				setNome("PH");
				setPalavras(new ArrayList<String>() {{
					add("mosaico");
					add("consolidações");
					add("mosaico e nods CL");
					add("aprisionamento lobular");
					add("PH crônica");
					add("PH");
				}});

				setSelected(Boolean.FALSE);
				setCytoscape_alias_list(new String[]{"PH"});
				setCanonicalName("PH");
				setSUID("2");
				setNodeType("RedWine");
				setName("PH");
				setShared_name("PH");
				setNodeTypeFormatted("RedWine");
			}});
//
//		add(new DoencaDTO(){{
//			setId(4);
//			setNome("silicose");
//			setPalavras(new String[] {
//					"nods",
//					"nodulos",
//					"intersticiais",
//					"bandas parenquimatosas"
//			});
//		}});
//
//		add(new DoencaDTO(){{
//			setId(5);
//			setNome("pneumocistose");
//			setPalavras(new String[] {
//					"cistos "
//			});
//		}});
//
//		add(new DoencaDTO(){{
//			setId(6);
//			setNome("cancer");
//			setPalavras(new String[] {
//					"nodulo semisolido"
//			});
//		}});
//
//		add(new DoencaDTO(){{
//			setId(7);
//			setNome("pneumonite actínica");
//			setPalavras(new String[] {
//					"micronodulos"
//			});
//		}});
//
//		add(new DoencaDTO(){{
//			setId(8);
//			setNome("esclerodermia");
//			setPalavras(new String[] {
//					"vidro fosco"
//			});
//		}});
		}};
	}
	
}
