# Annotation Trong Java Là Gì Và Chức Năng Của Nó?


## Nội Dung

1. Annotation trong Java là gì?
2. Cấu trúc của một Annotation trong Java.
3. Chức năng của Annotation trong Java.
4. Các Annotation tích hợp sẵn trong Java.
5. Khởi tạo Annotation (Annotation tự định nghĩa).


# 1. Annotation trong Java là gì?

- Annotation được hiểu là một dạng chú thích
  hoặc một dạng siêu dữ liệu (metadata) được dùng để cung cấp thông tin
  dữ liệu cho mã nguồn Java

- Các Annotation được sử dụng trong mã nguồn sẽ được biên dịch thành bytecode và sử dụng kỹ
  thuật phản chiếu (Reflection) để truy vấn thông tin siêu dữ liệu và đưa ra hành động thích
  hợp.Chúng ta có thể chú thích các lớp (class), phương thức (method), các biến (variable),
  các gói (package) và các tham số (prameter) trong Java.

- Java Annotation có hai loại:

Các Annotation được tích hợp sẵn.
Annotation do người dùng tự định nghĩa.

# 2. Cấu trúc của một Annotation trong Java

Một chú thích luôn bắt đầu với ký hiệu @ và sau đó là tên của chú thích. Ký hiệu @ chỉ ra cho trình biên dịch rằng đây là một chú thích.

Ví dụ: @Deprecated

Ký hiệu @ mô tả đây là một chú thích.
Deprecated là tên của chú thích
# 3. Chức năng của Annotation trong Java

Annotation được sử dụng trong Java cho 3 mục đích chính:

### Thứ nhất, chỉ dẫn cho trình biên dịch (Compiler)

Chú thích có thể được trình biên dịch sử dụng để phát hiện lỗi hoặc triệt tiêu các cảnh báo. Java có 3 Annotation có thể được sử dụng nhằm cung cấp chỉ dẫn cho trình biên dịch:

1. @Deprecated

2. @Override

3. @SuppressWarnings

Chi tiết về từng chú thích sẽ được giải thích chi tiết trong phần sau.

### Thứ hai, chỉ dẫn trong thời điểm biên dịch (Build-time)

Các công cụ phần mềm có thể thông qua các chỉ dẫn của chú thích để tạo mã nguồn, tệp XML, nén mã biên dịch và các tâp tin vào một tập tin, v…v

### Thứ ba, chỉ dẫn trong thời gian chạy (Runtime)

Thông thường, các Annotation không có mặt trong mã Java sau khi biên dịch. Tuy nhiên, có thể xác định trong thời gian chạy bằng cách sử dụng kỹ thuật Reflection và có thể sử dụng để đưa ra những hướng dẫn cho chương trình trong thời gian chạy.

# 4. Các Annotation tích hợp sẵn trong Java và ví dụ minh họa

## 1. Java Annotation được tích hợp để sử dụng trực tiếp trong code Java:

Chúng ta có 3 Annotation quan trọng:

***@Override***
Được sử dụng cho các phương thức có nghĩa là ghi đè một phương thức trong lớp cha (supperclass). Nếu một phương thức đánh dấu @Override không ghi đè chính xác một phương thức trong lớp cha của nó hay hiểu đơn giản là phương thức đó không hợp lệ thì trình biên dịch sẽ báo lỗi. Chúng ta không nhất thiết phải sử dụng @Override khi ghi đè phương thức, nhưng Annotation này sẽ giúp chúng ta tránh lỗi dễ dàng hơn.


Ví dụ:
```bash 
public class superExampleNMD {
    public void methodOverride() {
        System.out.println("SupperClass Dinh Duc Thien");
    }
}
```
```bash
public class ExampleNMD extends superExampleNMD {
    @Override
    public void methodOverride() {
        System.out.println("SubClass Dinh Duc Thien");
    }
}
```
```bash
public class MainNMD {
    public static void main(String[] args) {
        ExampleNMD ex = new ExampleNMD();
        ex.methodOverride();
    }    
}
```
Chúng ta có thể thấy rằng phương thức ở lớp con đã ghi đè nội dung lên phương thức của lớp cha.

***@Deprecated***

Được sử dụng để dánh dấu một đối tượng (class, method hoặc field) và chỉ dẫn rằng nó tốt nhất không nên được sử dụng nữa. Trình biên dịch sẽ đưa các câu cảnh báo khi chương trình sử dụng các thuộc tính, lớp hoặc phương thức có gắn với @Deprecated.

***@SuppressWarnings***

Thông báo cho trình biên dịch biết là không được in các câu cảnh báo nào đó.

Cú pháp sử dụng: @SuppressWarnings("...") hoặc @SuppressWarnings({"...","...",v...v...}) trong "..." được hiểu là tên các loại cảnh báo.

Chúng ta thường dùng:

@SuppressWarnings(“deprecation”) để thông báo trình biên dịch không cảnh báo việc sử dụng phương thức có sử dụng @Deprecated.

@SuppressWarnings(“unchecked”) để thông báo trình biên dịch không cảnh báo việc sử một ép kiểu không an toàn.

@SuppressWarnings(“rawtypes”) để thông báo trình biên dịch không cảnh báo lỗi trong khai báo kiểu dữ liệu.

## 2. Java Annotation được tích hợp sẵn được sử dụng trong Annotation khác.

***@Target***: Dùng để chú thích phạm vi sử dụng của một Annotation.  Các chú thích này đã được định nghĩa trong enum java.lang.annotation.ElementType:

* ***ElementType.TYPE***  Chú thích trên Class, interface, enum, annotation

* ***ElementType.FIELD*** Chú thích trường (field), bao gồm cả các hằng số enum.

* ***ElementType.METHOD*** Chú thích trên method.

* ***ElementType.PARAMETER*** Chú thích trên parameter.

* ***ElementType.CONSTRUCTOR*** Chú thích trên constructor.

* ***ElementType.LOCAL_VARIABLE*** Chú thích trên biến địa phương.

* ***ElementType.ANNOTATION_TYPE*** Chú thích trên Annotation khác.

* ***ElementType.PACKAGE*** Chú thích trên package.

***@Retention***: Dùng để chú thích mức độ tồn tại của một Annotation nào đó.
Cụ thể có 3 mức nhận thức tồn tại của vật được chú thích, và được định nghĩa trong enum java.lang.annotation.RetentionPolicy:
* ***RetentionPolicy.SOURCE***	Tồn tại trên mã nguồn, và không được trình biên dịch nhận ra.
* ***RetentionPolicy.CLASS***	Mức tồn tại được trình biên dịch nhận ra, nhưng không được nhận biết bởi máy ảo tại thời điểm chạy (Runtime).
* ***RetentionPolicy.RUNTIME***	Mức tồn tại lớn nhất, được trình biên dịch nhận biết, và máy ảo (JVM) cũng nhận ra khi chạy chương trình.
* ***@Inherited***: Chú thích này chỉ ra rằng chú thích mới nên được bao gồm trong tài liệu Java được tạo ra bởi các công cụ tạo tài liệu Java.
* ***@Documented***: Chú thích chỉ ra rằng loại chú thích có thể được kế thừa từ lớp cha và có giá trị mặc định là false. Khi người dùng truy vấn kiểu Annotation của lớp con và lớp con không có chú thích cho kiểu này thì lớp cha của lớp được truy vấn cho loại chú thích sẽ được gọi. Chú thích này chỉ áp dụng cho các khai báo class.

## 5. Khởi tạo Annotation (Annotation tự định nghĩa)

Annotation khá giống một interface, để khai báo một Annotation chúng ta sử dụng @interface. Annotation có thể có hoặc không có các phần tử (element) trong đó.

Một phần tử của Annotation có các đặc điểm như sau:

**Không có thân hàm**
Không có tham số hàm
Khai báo trả về phải là một kiểu dữ liệu cụ thể (Kiểu nguyên thủy, Enum, Annotation hoặc Class).

**Có thể có giá trị mặc định**.
Một Annotation sẽ đượng định nghĩa bởi các Meta-Annotations. Các Meta-Annotations gồm @Retention, @Target, @Documented, @Inherited.

## 6 @Congiguration
Được sử dụng để chỉ ra rằng class khai báo sử dụng annotation @Configuration sẽ khai báo một hoặc nhiều @Bean method trong class đó. Những class khai báo với @Configuration sẽ được Spring container quản lý và tạo bean trong lúc chương trình đang chạy.

## 7  @Bean
Method (phương thức) sử dụng @Bean ở phía trên mình để chỉ ra rằng . Method đó sẽ sản xuất ra đối tượng bean và được quản lý bởi spring container . Bean annotation có thể sử dụng với các tham số như name, initMethod hoặc destroyMethod

## 8 @PreDetroy và @PostConstruct
Đây là cách dùng khác để quản lý vòng đời của Bean. Ngoài cách sử dụng initMethod và destroyMethod. Ta có thể sử dụng @PreDetroy và @PostConstruct với cùng một mục đích

## 9 @ComponentScan
Chúng ta sử dụng @ComponentScan để thông báo Spring Container biết phải vào package nào trong dự án để quét các Annotation và tạo Bean.

## 10 @Component
Khi một class được đánh dấu là @Component thì sẽ được tạo thành 1 bean. Khi Spring start thì nó quét qua các annotation có đánh dấu là @Component thì nó sẽ tạo bean cho class đó. Ví dụ ta có class Contact và ta đánh dấu nó là @Component thì Spring khi đọc qua class này nó sẽ tạo 1 bean có tên là contact trong container của nó. Nếu có class nào dùng thì nó sẽ nhúng bean này vào. Dùng @component là để tạo ra một bean

## 11 @PropertySource và @Value
Trong Spring chúng ta sử dụng @PropertySource để cho Spring biết tìm các file properties cấu hình cho hệ thống ở đâu đồng thời sử dụng @Value để lấy các giá trị trong file properties

Ví dụ bên dưới ta sử dụng classpath để khai báo file properties ta đặt ở đâu trong dự án. Tiếp đến ta sử dụng @Value để lấy các giá trị trong file properties với key tương ứng và gán vào biến mà ta sẽ sử dụng.

## 12 @Service
Nếu một class được đánh dấu là @Service thì nó là kiểu đặt biệt của @Component. Nó được dùng để xử lý các nghiệp vụ của ứng dụng. Ví dụ như kế toán thì có nghiệp vụ là kiểm tra chi, quản lý thu. Lớp BookServiceImpl dưới đây được đánh dấu là @Service thì nó sẽ phụ trách xử lý các vấn đề liên quan đến nghiệp vụ.

## 13 @Repository
Nếu một class được đánh dấu là @Repository thì nó là kiểu đặt biệt của @Component . Nó được sử dụng để nói bean này dùng để truy cập và thao tác xuống cơ sở dữ liệu. Class BookDaoImpl được đánh dấu với @Repository nghĩa là lớp này có nhiệm vụ thực hiện các câu lệnh truy vấn xuống database.

## 14  @Autowire
Tự động nhúng các bean được Spring Container sinh ra vào Class có khai báo @Autowire. Khi Spring nó sẽ tìm kiếm bean có tên là BookDao trong container của nó ,sau đó nhúng (hoặc tiêm) vào lớp BookServiceImple. Đây chính là cơ chế DI (dependency injection). Khi Spring bắt đầu chạy nó sẽ quét qua các lớp có sử dụng annotation để tạo bean đồng thời nó cũng quét bên trong các bean xem có khai báo @Autowire không nếu có nó sẽ tìm kiếm bean tương ứng mà nó quản lý và nhúng vào.

## 15 @Scope
Khi bean được tạo ra thì nó có nhiều scope khác nhau. @Scope ở đây là phạm vi bean được sinh và và bị phá huỷ dưới sự quản lý của Spring Container. Khi bean được sinh ra nó có 5 scope (phạm vi được sử dụng)

singleton : đây là scope mặc định của 1 bean khi được sinh ra. Nếu ta không khai báo scope cụ thể thì bean sẽ lấy singleton scope. Singleton bean có nghĩa là bean chỉ tạo ra 1 lần và được sử dụng trong container. Chỉ duy nhất 1 bean tồn tại trong container.
prototype : ngược lại với singleton ta muốn có nhiều bean (đối tượng) thì ta sử dụng scope prototype.
Request : Bean được sinh ra thông qua các request http (yêu cầu) từ người dùng. Chỉ được dùng trong các ứng dụng web.
Session : Bean được sinh ra thông qua các http session.
Global-session : Bean được sinh ra thông qua các request http (yêu cầu) từ người dùng. Chỉ được dùng trong các ứng dụng web.

## 16 @Valid
Dùng để kiểm tra dữ liệu có đúng như mình mong muốn hay không. Ví dụ dưới đây mình mong muốn name là không được rỗng , author không được rỗng. Nếu dữ liệu bị rỗng thì @Validate sẽ bắt lỗi.

## 17 @Controller
Một class được đánh dấu là @Controller thì để khai báo Class đó là một controller và có nhiệm vụ mapping request trên url vào các method tương ứng trong controller. Ví dụ dưới đây mình khai báo Class HomeController là một Controller

## 18 @RequestMapping
Có nhiệm vụ ánh xạ các request (yêu cầu) người dùng vào method tương ứng trong controller. Ví dụ : Khi ta nhập vào url là http://localhost:8080/method2 thì nó sẽ được xử lý bởi phương thức là public String method2().

## 19 @PathVariable
@PathVariable được sử dụng để xử lý những URI động, có một hoặc nhiều parameter trên URI.

## 20 @RequestParam
Chúng ta sử dụng @RequestParame để bắt các giá trị các tham số mà người dùng truyền vào trên url theo định dạng key và value.

Ví dụ mình có cái link sau http://localhost:8080/api/foos?id=abc . Bây giờ mình muốn lấy giá trị abc của tham số id trên url thì mình sẽ dùng @RequestParam . Ở đây mình khai báo giá trị tham số trên URL theo định dạng key = value (id=abc).

## 21 @ModelAttribute
Một trong những annotation quan trọng trong Spring đó là @ModelAttribute. Chúng ta sử dụng ModelAttribute như một cầu nối giữa Controller và View.

## 22  @RequestBody
được sử dụng để lấy các giá trị mà người dùng gửi lên server mà các giá trị đó được chứa trong phần thân (body) của request

## 23 @ResponseBody
Chúng ta sử dụng @ResponseBody để nói cho controller biết rằng ta sẽ trả về một đối tượng Object kiểu Json cho client chứ mình không render ra một trang view.

## 24 @RequestHeader và @ResponseHeader
@RequestHeader được sử dụng khi ta muốn lấy dữ liệu được truyền bằng Header của một request (yêu cầu từ client)



## License
[MIT](https://choosealicense.com/licenses/mit/)