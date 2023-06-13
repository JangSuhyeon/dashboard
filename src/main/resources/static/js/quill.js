
var quill;
$(document).ready(function() {

    // QUill
    var toolbarOptions = [
        ["bold", "italic", "underline", "strike"], // 굵게, 이탤릭, 밑줄, 취소선
        [{ "header": 1 }, { "header": 2 }], // 헤더 1, 헤더 2
        [{ "list": "ordered" }, { "list": "bullet" }], // 번호 매기기, 글머리 기호
        ["link", "image"], // 링크, 이미지
        ["clean"] // 모든 스타일 제거
    ];

    quill = new Quill(".editor-container", {
        modules: {
            toolbar: toolbarOptions
        },
        theme: "snow" // 테마 설정 ("snow" 또는 "bubble")
    });

});