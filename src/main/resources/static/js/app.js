let app = {
    init: function () {
        let _this = this;
        $('#btn-section-save').on('click', function () {
            _this.sectionSave();
        });

        $('#btn-section-update').on('click', function () {
            _this.sectionUpdate();
        });
        
        $('#btn-section-delete').on('click', function () {
            _this.sectionDelete();
        });

        $('#btn-category-save').on('click', function () {
            _this.categorySave();
        });

        $('#btn-category-update').on('click', function () {
            _this.categoryUpdate();
        });

        $('#btn-category-delete').on('click', function () {
            _this.categoryDelete();
        });
    },

    sectionSave: function () {
        let data = {
            name: $('#name').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/section',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('섹션이 저장되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    sectionUpdate: function () {
        let data = {
            name: $('#name').val()
        };
        let id = $('#section-id').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/section/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('섹션이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    
    sectionDelete: function () {
        let id = $('#section-id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/section/' + id,
            dataType: 'json'
        }).done(function () {
            alert('섹션이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categorySave: function () {
        let data = {
            name: $('#name').val(),
            color: $('#color').val(),
            sectionId: $('#select-section').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/category',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('카테고리가 저장되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryUpdate: function () {
        let id = $('#category-id').val();
        let data = {
            sectionId: $('#select-section').val(),
            name: $('#name').val(),
            color: $('#color').val()
        };
        $.ajax({
            type: 'PUT',
            url: '/api/v1/category/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('카테고리가 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    categoryDelete: function () {
        let id = $('#category-id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/category/' + id,
            dataType: 'json'
        }).done(function () {
            alert('카테고리가 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

app.init();

console.log("app.js loaded");