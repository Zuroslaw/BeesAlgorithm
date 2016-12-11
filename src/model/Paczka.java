package model;

import java.util.ArrayList;
import java.util.List;

public class Paczka {
	
	private double max_waga;
	private double act_waga = 0;
	private double act_jakosc = 0;
	private ArrayList<Integer> listaNumerow = new ArrayList<Integer>();
	
	private List<Przedmiot> listaPrzedmiotow = new ArrayList<>();
	
	public ArrayList<Integer> getListaNumerow() {
		return listaNumerow;
	}

	public void setListaNumerow(ArrayList<Integer> list) {
		this.listaNumerow = list;
	}
	
	
	public Paczka(double max_waga_) {
		this.max_waga = max_waga_;
	}
	
	public double getMax_waga() {
		return max_waga;
	}

	public void setMax_waga(double max_waga) {
		this.max_waga = max_waga;
	}

	public double getAct_waga() {
		return act_waga;
	}

	public void setAct_waga(double act_waga) {
		this.act_waga = act_waga;
	}
	
	public double getAct_jakosc() {
		return act_jakosc;
	}

	public List<Przedmiot> getListaPrzedmiotow() {
		return listaPrzedmiotow;
	}

	public void setListaPrzedmiotow(List<Przedmiot> listaPrzedmiotow) {
		this.listaPrzedmiotow = listaPrzedmiotow;
	}

	public void setAct_jakosc(double act_jakosc) {
		this.act_jakosc = act_jakosc;
	}

	public boolean addPrzedmiot(Przedmiot przedmiot)
	{
		if (act_waga + przedmiot.getWaga() > max_waga) return false;
		
		listaPrzedmiotow.add(przedmiot);
		act_waga += przedmiot.getWaga();
		act_jakosc += przedmiot.getCena();
		return true;
	}

	@Override
	public String toString() {
		return "Paczka [max_waga=" + max_waga + ", act_waga=" + act_waga + ", act_jakosc=" + act_jakosc
				+ ", listaPrzedmiotow=" + listaPrzedmiotow + "]";
	}
	public double Jakosc(){
		return act_jakosc;
	}
}

//min f(x) = x1.cena +x2.cena+ x3.cena + x4.cena ...../
 // min f(x) = x1.f(jakosci) + x2.F(jakosci) + x3.f(jakosci) + ......
//f(jakosci)=priorytet*cena + priorytet*jakosc - kara za brak
