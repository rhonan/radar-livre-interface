package br.ufc.quixada.pojo;

public class Observacao {

	private double latitude;
	private double longitude;
	private String aeronave_hex;
	private String status;
	private int grau;
	private double velocidade;
	private int altitude;
	
	public Observacao(double latitude, double longitude, double velocidade, int altitude, int grau, String aeronave_hex, String status){
		this.latitude = latitude;
		this.longitude = longitude;
		this.velocidade = velocidade;
		this.altitude = altitude;
		this.grau = grau;
		this.aeronave_hex = aeronave_hex;
		this.status = status;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getAeronave_hex() {
		return aeronave_hex;
	}
	public void setAeronave_hex(String aeronave_hex) {
		this.aeronave_hex = aeronave_hex;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}

	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

}
