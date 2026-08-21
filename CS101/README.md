# CS 101 - Introduction to Programming (Java)

Bu depo, CS 101 dersi kapsamında Java kullanılarak hazırlanan haftalık laboratuvar (lab) çalışmalarını içermektedir. Her laboratuvarda, programlama temellerinden başlayıp Nesne Yönelimli Programlama (OOP) kavramlarına kadar uzanan çeşitli konular ele alınmıştır. İçerisinde uygulamaların açıklamalarını barındıran PDF yönergeleri ve kaynak kodları mevcuttur.

## Laboratuvar İçerikleri

### Lab 1: Temel Değişkenler ve Çıktı İşlemleri
Bu laboratuvarda temel veri tipleri (int, double, String), sabit değişken (`final`) tanımlamaları ve basit aritmetik işlemler kullanılmıştır. Konsola formatlı metin ve hesaplama sonuçları yazdırma (`System.out.println`) işlemleri üzerinde durulmuştur. Basit bir not ağırlık ve yüzde hesaplama senaryosu kodlanmıştır.

### Lab 2: Kullanıcı Girişi (Scanner) ve Matematiksel İşlemler
Kullanıcıdan veri almak için `Scanner` sınıfının temelleri atılmıştır. `Math.sqrt` ve `Math.pow` gibi matematik kütüphanesi fonksiyonları kullanılarak geometrik hesaplamalar (taban uzunluğu, yükseklik) yapılmıştır. Üç boyutlu cisimlerin hacim ve yüzey alanı hesaplamalarına odaklanılmıştır.

### Lab 3: Koşullu İfadeler (If/Else) ve Mantıksal Operatörler
Müşteri yaşı ve bilet türü (IMAX, 3D, Regular) gibi farklı parametrelere göre bilet fiyatlandırması yapan bir sinema sistemi mantığı kurgulanmıştır. Programın akışını yönlendirmek için iç içe `if/else` blokları ve `boolean` mantıksal operatörleri yoğun bir şekilde kullanılmıştır. 

### Lab 4: Döngüler (Loops) ile Şekil Çizdirme
Kullanıcıdan geçerli (tek sayı ve pozitif) bir girdi alana kadar `while` döngüsü ile hatalı giriş (validation) kontrolü sağlanmıştır. Alınan girdi kullanılarak iç içe `for` döngüleri yardımıyla konsola bir kum saati (hourglass) deseni çizdirilmiş, algoritmik düşünme becerileri pekiştirilmiştir.

### Lab 5: Metot Tanımlama ve Karakter (Char) İşlemleri
Programı daha modüler ve tekrar kullanılabilir hale getirmek için kendi metotlarımızı yazmaya başladık. `isAlphabetic`, `toUpper` ve `isSeparator` gibi özel karakter işleme fonksiyonları yazılarak temel ASCII mantığı kavranmış, string ve karakter (char) manipülasyonu uygulanmıştır.

### Lab 6: İki Boyutlu Diziler (2D Arrays)
Kullanıcıdan alınan satır ve sütun boyutlarıyla iki boyutlu diziler (matrisler) oluşturulmuştur. İç içe döngüler kullanılarak bu diziye yükseklik (elevation) verileri doldurulmuş ve bir arazi haritası (Terrain Map) simülasyonu üzerinde veri çekme/işleme çalışmaları yapılmıştır.

### Lab 7: Nesne Yönelimli Programlamaya Giriş (Minesweeper)
Bu laboratuvarda klasik Mayın Tarlası (Minesweeper) oyununun altyapısı Nesne Yönelimli Programlama (OOP) ilkeleriyle kurgulanmıştır. `Board`, `Cell` ve `GameController` isimli sınıflar (classes) tasarlanarak, nesneler arası iletişim ve durum (state) yönetimi pratik edilmiştir.

### Lab 8: İleri Seviye OOP ve Oyun Motoru Mantığı
Bir oyun altyapısı (Game Engine) kurularak `Entity`, `HealthStone`, `Star` ve `Drawer` gibi birbirleriyle ilişkili çeşitli sınıflar tasarlanmıştır. Nesnelerin oyun alanı (Game Field) üzerindeki koordinatları, can değerleri (health) ve birbiriyle etkileşimleri modellenerek kapsülleme (encapsulation) kullanılmıştır.

### Lab 9: Kapsamlı OOP Sistem Tasarımı (Restoran Otomasyonu)
Öğrenilen tüm Nesne Yönelimli Programlama (OOP) konseptleri birleştirilerek sipariş alan bir restoran sistemi kodlanmıştır. `Pizza`, `Salad` gibi menü ürünleri modellenmiş, `Ingredient` (malzeme), `Order` (sipariş) ve ana `Restaurant` modülleri tasarlanarak modüler bir proje ortaya çıkarılmıştır.
