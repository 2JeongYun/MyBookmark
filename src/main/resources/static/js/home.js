let home = {
    init: function () {
        let _this = this;
        $('#category-save-btn').on('click', function () {
            _this.categorySave();
        });
    },

    categorySave: function () {
        let data = {
            name: $('#name').val(),
            color: $('#color').val()
        };

        $.ajax({
           type: 'POST',
           url: '/api/v1/category',
           dataType: 'json',
           contentType: 'application/json; charset=utf-8',
           data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

home.init();