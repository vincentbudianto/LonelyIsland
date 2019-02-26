/* *** Lonely Island *** */

/* FORMAT INPUT FILE EKSTERNAL */
1. Baris pertama berisi tiga buah bilangan integer
   n (banyaknya pulau); m(banyaknya jembatan); r (indeks pulau posisi awal pemain);
2. Sejumlah m baris berikutnya (banyaknya jembatan) berisi dua buah bilangan integer
   setiap barisnya yang merepresentasikan dari pulau u dapat menuju pulau v namun
   tidak sebaliknya (satu arah)

   Batasan:
   1 =< n =< 200.000
   1 =< m =< 500.000
   1 =< r =< n 
   1 =< u
   1 =< v

   Contoh File Eksternal:
   5 7 1
   1 2
   1 3
   1 4
   1 5
   2 4
   2 5
   3 4

/* CARA MENGCOMPILE PROGRAM PADA COMMAND LINE */
1. Buka Command Line di file direktori source program (file src)
2. Ketikkan "javac LonelyIsland.java" untuk mengcompile source code

/* CARA MENJALANKAN PROGRAM PADA COMMAND LINE */
1. Buka Command Line di file direktori source program (file src)
2. Ketikkan:
   a. "java LonelyIsland" untuk menjalankan program tanpa argumen apapun
      (File input.txt akan otomatis terbaca)
   b. "java LonelyIsland xxxx.txt" untuk menjalankan program dengan 1 argumen
      (File akan membaca xxxx.txt dan otomatis menuliskan hasil pada file output.txt)
   c. "java LonelyIsland xxxx.txt yyyy.txt" untuk menjalankan program dengan 2 argumen
      (File akan membaca xxxx.txt dan menuliskan hasil pada file yyyy.txt)