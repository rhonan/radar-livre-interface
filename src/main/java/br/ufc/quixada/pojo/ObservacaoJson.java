package br.ufc.quixada.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="observacao")

public class ObservacaoJson {
	
	private String radar;
	private String latitude;
	private String longitude;
	private String altitude;
	private String velocidade;
	private String angulo;
	private String hora;
	private String roda_id;
	private String hex;
	
	public ObservacaoJson(String radar, String latitude, String longitude,
			String altitude, String velocidade, String angulo, String hora,
			String roda_id, String hex) {
		super();
		this.radar = radar;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.velocidade = velocidade;
		this.angulo = angulo;
		this.hora = hora;
		this.roda_id = roda_id;
		this.hex = hex;
	}
	
	public ObservacaoJson(){
		
	}
	
	public String getRadar() {
		return radar;
	}
	public void setRadar(String radar) {
		this.radar = radar;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}
	public String getAngulo() {
		return angulo;
	}
	public void setAngulo(String angulo) {
		this.angulo = angulo;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getRoda_id() {
		return roda_id;
	}
	public void setRoda_id(String roda_id) {
		this.roda_id = roda_id;
	}
	public String getHex() {
		return hex;
	}
	public void setHex(String hex) {
		this.hex = hex;
	}
}
