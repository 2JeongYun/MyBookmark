let app = {
    init: function () {
        let _this = this;
        
        // init category save modal
        $('#category-create-btn').on('click', function () {
            _this.categorySave();
        });

        // init category modify modal
        let id;
        $('#category-delete-btn').on('click', function () {
            if (id > 0) {
                _this.categoryDelete(id);
            }
        });
        $('#category-modify-btn').on('click', function () {
            if (id > 0) {
                _this.categoryUpdate(id);
            }
        });

        $('#category-modify-modal').on('show.bs.modal', function (e) {
            id = e.relatedTarget.getAttribute('data-id');
            console.log(id);
            $('#category-modify-modal-name').
                    attr('placeholder', e.relatedTarget.innerHTML);
            console.log(e.relatedTarget.innerHTML);
            $('#category-modify-modal-color').val(rgbToHex(e.relatedTarget.style.color));
        });
        $('#category-modify-modal').on('hide.bs.modal', function (e) {
            id = -1;
            $('#category-modify-modal-name').val("");
        });
    },

    categorySave: function () {
        let _this = this;
        let data = {
            name: $('#category-create-modal-name').val(),
            color: $('#category-create-modal-color').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/category',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('카테고리가 생성되었습니다.');
            _this.getCategoriesFromServer();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryUpdate: function (id) {
        let _this = this;
        let data = {
            name: $('#category-modify-modal-name').val(),
            color: $('#category-modify-modal-color').val()
        };
        if (data.name === '') {
            data.name = $('#category-modify-modal-name').attr('placeholder');
        }

        $.ajax({
            type: 'PUT',
            url: '/api/v1/category/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('카테고리가 수정되었습니다');
            _this.getCategoriesFromServer();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryDelete: function (id) {
        let _this = this;

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/category/' + id,
            dataType: 'json',
        }).done(function () {
            alert('카테고리가 삭제되었습니다.');
            _this.getCategoriesFromServer();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    getCategoriesFromServer: function () {
        let _this = this;

        $.ajax({
            type: 'GET',
            url: '/api/v1/category',
            dataType: 'json',
        }).done(function (data) {
            _this.renderCategories(data);
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    renderCategories: function (data) {
        let template = $('#category-template').html();
        let output = "";
        for (let category of data) {
            output += Mustache.render(template, category);
        }
        $('#category-container').html(output);
    }
}

function rgbToHex ( rgbType ){
    /*
    ** 컬러값과 쉼표만 남기고 삭제하기.
    ** 쉼표(,)를 기준으로 분리해서, 배열에 담기.
    */
    let rgb = rgbType.replace( /[^%,.\d]/g, "" ).split( "," );

    rgb.forEach(function (str, x, arr){

        /* 컬러값이 "%"일 경우, 변환하기. */
        if ( str.indexOf( "%" ) > -1 ) str = Math.round( parseFloat(str) * 2.55 );

        /* 16진수 문자로 변환하기. */
        str = parseInt( str, 10 ).toString( 16 );
        if ( str.length === 1 ) str = "0" + str;

        arr[ x ] = str;
    });

    return "#" + rgb.join( "" );
}

app.init();
$(document).ready(function () {
    app.getCategoriesFromServer();
});

console.log("app.js loaded");