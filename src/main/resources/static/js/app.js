let app = {
    init: function () {
        let _this = this;
        $('#category-save-btn').on('click', function () {
            _this.categorySave();
        });
    },

    categorySave: function () {
        let _this = this;
        let data = {
            name: $('#name').val(),
            color: $('#color').val()
        };

        $.ajax({
           type: 'POST',
           url: '/api/v1/category',
           dataType: 'json',
           contentType: 'application/json; charset=utf-8',
           data: JSON.stringify(data),
        }).done(function () {
            alert('글이 등록되었습니다.');
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
            contentType: 'application/json; charset=utf-8',
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

app.init();
$(document).ready(function () {
   app.getCategoriesFromServer();
});

console.log("app.js loaded");