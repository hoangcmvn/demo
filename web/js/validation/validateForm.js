function validationForm(){     
   $('#validate-form-book').validate({
           rules:{
               bname: {
                   required:true
               },
               author: {
                   required:true
               },
               publication_year: {
                   required:true,
                   number: true,
                   min:0
               },
               numberpage: {
                   required:true,
                   number: true,
                   min:0
               },
               location:  {
                   required:true
               },
               descrip: {
                   required:true
               },
               img: {
                   required:true
               }
           },
           messages:{
                bname:{
                   required:"Tên sách không được bỏ trống"
                },
                author: {
                   required:"Tên tác giả không được bỏ trống"
                },
                publication_year: {
                   required:"Năm xuất bản không được bỏ trống",
                   number: "Chỉ được nhập số năm - không được nhập kí tự và chữ",
                   min: "Số năm phải hợp lệ - số năm phải > 0"
               },
               numberpage: {
                   required:"Số trang không được bỏ trống",
                   number: "Chỉ được nhập được số",
                   min: "Số trang phải hợp lệ - số trang phải > 0"
               },
               location:  {
                   required:"Vị trí sách không được bỏ trống"
               },
               descrip: {
                   required:"Mô tả sách không được bỏ trống"
               },
               img: {
                   required:"Sách cần có ảnh"
               }
            }
        });
}
function validateUpdateBook(){     
   $('#validate-form-update').validate({
           rules:{
               bname: {
                   required:true
               },
               author: {
                   required:true
               },
               publication_year: {
                   required:true,
                   number: true,
                   min:0
               },
               numberpage: {
                   required:true,
                   number: true,
                   min:0
               },
               location:  {
                   required:true
               },
               descrip: {
                   required:true
               },
           },
           messages:{
                bname:{
                   required:"Tên sách không được bỏ trống"
                },
                author: {
                   required:"Tên tác giả không được bỏ trống"
                },
                publication_year: {
                   required:"Năm xuất bản không được bỏ trống",
                   number: "Chỉ được nhập số năm - không được nhập kí tự và chữ",
                   min: "Số năm phải hợp lệ - số năm phải > 0"
               },
               numberpage: {
                   required:"Số trang không được bỏ trống",
                   number: "Chỉ được nhập được số",
                   min: "Số trang phải hợp lệ - số trang phải > 0"
               },
               location:  {
                   required:"Vị trí sách không được bỏ trống"
               },
               descrip: {
                   required:"Mô tả sách không được bỏ trống"
               },
            }
        });
}