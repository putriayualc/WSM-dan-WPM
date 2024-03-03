public class Apartemen {
    private double harga;
    private int tahun, fasilitas, keamanan;
    private float jarak;
    private float WSM, WPM;
    private String nama;
    
    Apartemen(double harga, int tahun, int fasilitas, int keamanan, float jarak, String nama) {
        this.harga = harga;
        this.tahun = tahun;
        this.fasilitas = fasilitas;
        this.keamanan = keamanan;
        this.jarak = jarak;
        this.nama = nama;
    }

    Apartemen(){}

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(int fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getKeamanan() {
        return keamanan;
    }

    public void setKeamanan(int keamanan) {
        this.keamanan = keamanan;
    }

    public float getJarak() {
        return jarak;
    }

    public void setJarak(float jarak) {
        this.jarak = jarak;
    }
    
    public float getWSM() {
        return WSM;
    }

    public void setWSM(float wSM) {
        WSM = wSM;
    }

    public float getWPM() {
        return WPM;
    }

    public void setWPM(float wPM) {
        WPM = wPM;
    }

    public int skorHarga(){
        if (this.harga >= 10000000) {
            return 1;
        }else if (this.harga >= 5000000 && this.harga < 10000000) {
            return 2;
        }else if (this.harga >= 1000000 && this.harga < 5000000){
            return 3;
        }
        return 4;
    }

    public int skorTahun(){
        if (this.tahun >= 2015) {
            return 3;
        }else if (this.tahun >= 2010 && this.tahun < 2015) {
            return 2;
        }else if (this.tahun >= 2005 && this.tahun < 2010){
            return 1;
        }return 0;
    }
    
    public int skorJarak(){
        if (this.jarak >= 5) {
            return 1;
        }else if (this.jarak >= 2 && this.jarak < 5) {
            return 2;
        }else {
            return 3;
        }
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
