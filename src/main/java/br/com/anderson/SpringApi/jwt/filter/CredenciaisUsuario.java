package br.com.anderson.SpringApi.jwt.filter;

//import lombok.Data;//lombok para criar os getters e setters dos atributos

//@Data
public class CredenciaisUsuario {
	
	private String usunome;
	private String ususenha;
	
	public CredenciaisUsuario() {}
	
	public CredenciaisUsuario(String usunome, String ususenha) {
		this.usunome = usunome;
		this.ususenha = ususenha;
	}

	public String getUsunome() {
		return usunome;
	}

	public void setUsunome(String usunome) {
		this.usunome = usunome;
	}

	public String getUsusenha() {
		return ususenha;
	}

	public void setUsusenha(String ususenha) {
		this.ususenha = ususenha;
	}
	
}
