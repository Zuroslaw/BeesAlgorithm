package model;

public class Przedmiot {
	
	private String nazwa;
	private int pozycja;
	private double waga;
	private double cena;
	
	public Przedmiot(String nazwa_, double waga_, double cena_)
	{
		this.nazwa = nazwa_;
		this.waga=waga_;
		this.cena=cena_;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public double getWaga() {
		return waga;
	}
	public void setWaga(double waga) {
		this.waga = waga;
	}
	
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	@Override
	public String toString() {
		return "Przedmiot [nazwa=" + nazwa + ", waga=" + waga + ", cena=" + cena + ", pozycja= " + pozycja + " ]";
	}

	public int getPozycja() {
		return pozycja;
	}

	public void setPozycja(int pozycja) {
		this.pozycja = pozycja;
	}
	
}
