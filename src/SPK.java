import java.util.Scanner;

public class SPK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        float[] bobot = { 0.30f , 0.20f, 0.20f, 0.20f, 0.10f };

        System.out.println("SPK Pemilihan Apartemen Tinggal");
        System.out.println("===============================");
        System.out.print("Masukkan jumlah Apartemen : ");
        int jmlApart = sc.nextInt();
        Apartemen[] apt = new Apartemen[jmlApart];

        for (int i = 0; i < jmlApart; i++) {
           System.out.println("__________________________________");
           System.out.print("Fasilitas Apartemen "+ (i+1) +" (1-4)  : ");
           int fasilitas = sc.nextInt();
           System.out.print("Harga Apartemen "+ (i+1) +" (Rp)       : ");
           double harga = sc.nextDouble();
           System.out.print("Tahun konstruksi Apartemen "+ (i+1) +" : ");
           int tahun = sc.nextInt();
           System.out.print("Jarak Apartemen "+ (i+1) +" (Km)       : ");
           float jarak = sc.nextFloat();
           System.out.print("Keamanan Apartemen "+ (i+1) +" (1-4)   : ");
           int keamanan = sc.nextInt();

           apt[i] = new Apartemen(harga, tahun, fasilitas, keamanan, jarak, ("Apartemen " + i));
        };
        System.out.println("__________________________________");

        //membuat matriks keputusan dan melakukan normalisasi
        float[][] proses = normalisasi(matriksKeputusan(jmlApart, apt));
        //menghitung bobot
        proses = pembobotan(bobot, proses);
        //menghitung wsm dan wpm
        apt = wsm(apt, proses);
        apt = wpm(apt, proses, bobot);

        System.out.println("_____________________________________");
        System.out.println("                 WSM            WPM");
        for (Apartemen p : apt) {
            System.out.printf("%s    %.4f         %.4f\n", p.getWSM(), p.getWPM());
        }
        System.out.println("_____________________________________");
        System.out.println("Jadi alternatif terbaik yaitu " + maxWPM(apt).getNama());
    }

    public static int[][] matriksKeputusan(int alter, Apartemen[] apt){
        int matriks[][] = new int[alter][5];

        for (int i = 0; i < matriks.length; i++) {
            matriks[i][0] = apt[i].getFasilitas();
            matriks[i][1] = apt[i].skorHarga();
            matriks[i][2] = apt[i].skorTahun();
            matriks[i][3] = apt[i].skorJarak();
            matriks[i][4] = apt[i].getKeamanan();
        }

        //menampilkan matriks
        System.out.println("    Matriks Keputusan");
        System.out.println("-------------------------");
        for (int[] i : matriks) {
            for (int j : i) {
                System.out.print(j + "    ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");

        return matriks;
    }

    public static float[][] normalisasi(int[][] matriks){
        float[][] normali = new float[matriks.length][matriks[0].length]; 
        for (int i = 0; i < matriks.length; i++) {
            for (int j = 0; j < matriks[0].length; j++) {
                if (j == 1 || j == 3) {
                    normali[i][j] = cost(i, j, matriks);
                }
                else{
                    normali[i][j] =benefit(i, j, matriks);
                }
            }
        }

        //menampilkan normalisasi
        System.out.println("       Normalisasi");
        System.out.println("-------------------------");
        for (float[] i : normali) {
            for (float j : i) {
                System.out.printf("%.2f ", j);
            }
            System.out.println();
        }
        System.out.println("-------------------------");

        return normali;
    }

    public static float cost(int bar, int kol, int[][] matriks){
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < matriks.length; i++) {
            if (matriks[i][kol] < min) {
                min = matriks[i][kol];
            }
        }

        float c = min / (float) matriks[bar][kol];

        return c;
    }

    public static float benefit(int bar, int kol, int[][] matriks){
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < matriks.length; i++) {
            if (matriks[i][kol] > max) {
                max = matriks[i][kol];
            }
        }

        float b = (float) matriks[bar][kol]/max;

        return b;
    }

    public static float[][] pembobotan(float[] bbt, float[][] normal){
        for (int i = 0; i < normal.length; i++) {
            for (int j = 0; j < normal[0].length; j++) {
                normal[i][j] = normal[i][j] * bbt[j];
            }
        }

        //menampilkan pembobotan
        System.out.println("    Menghitung Bobot");
        System.out.println("-------------------------");
        for (float[] fs : normal) {
            for (float fs2 : fs) {
                System.out.printf( "%.3f ", fs2);
            }
            System.out.println();
        }
        System.out.println("-------------------------");

        return normal;
    }

    public static Apartemen[] wsm(Apartemen[] apt, float[][] pbb){
        for (int i = 0; i < pbb.length; i++) {
            float wsm = 0;
            for (int j = 0; j < pbb[0].length; j++) {
                wsm += pbb[i][j];
            }
            apt[i].setWSM(wsm);
        }
        return apt;
    }

    public static Apartemen[] wpm(Apartemen[] apt, float[][] pbb, float[] bbt){
        for (int i = 0; i < pbb.length; i++) {
            float wpm = 1;
            for (int j = 0; j < pbb[0].length; j++) {
                float pangkat = (float) Math.pow(pbb[i][j], bbt[j]);
                wpm = wpm * pangkat;
            }
            apt[i].setWPM(wpm);
        }
        return apt;
    }

    public static Apartemen maxWPM(Apartemen[] apt){
        float max = 0;
        Apartemen mx = new Apartemen();
        for (int i = 0; i < apt.length; i++) {
            if (apt[i].getWPM() > max) {
                mx = apt[i];
            }
        }
        return mx;
    }
}
