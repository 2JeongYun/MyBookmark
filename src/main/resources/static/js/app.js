const url = new URL(window.location.href);
const urlParams = url.searchParams;
let prevSectionId = urlParams.get('prevSectionId');
if (prevSectionId == undefined) {
    prevSectionId = "";
}

console.log(prevSectionId);
let app = {
    init: function () {
        let _this = this;
        $('#btn-section-save').on('click', function () {
            _this.saveSection();
        });

        $('#btn-section-update').on('click', function () {
            _this.updateSection();
        });

        $('#btn-section-delete').on('click', function () {
            _this.deleteSection();
        });

        $('#btn-category-save').on('click', function () {
            _this.saveCategory();
        });

        $('#btn-category-update').on('click', function () {
            _this.updateCategory();
        });

        $('#btn-category-delete').on('click', function () {
            _this.deleteCategory();
        });

        $('#btn-bookmark-save').on('click', function () {
            _this.saveBookmark();
        }),

        $('.a-bookmark').on('click', function (event) {
            _this.addOpenCount(event);
        })

        $('.btn-cancel').on('click', function () {
            window.location.href = '/' + prevSectionId;
        })
    },

    saveSection: function () {
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
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    updateSection: function () {
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
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteSection: function () {
        let id = $('#section-id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/section/' + id,
            dataType: 'json'
        }).done(function () {
            alert('섹션이 삭제되었습니다.');
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    saveCategory: function () {
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
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    updateCategory: function () {
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
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteCategory: function () {
        let id = $('#category-id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/category/' + id,
            dataType: 'json'
        }).done(function () {
            alert('카테고리가 삭제되었습니다.');
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    saveBookmark: function () {
        let data = {
            categoryId: $('#select-category').val(),
            address: $('#address').val(),
            alias: $('#alias').val(),
            description: $('#description').val(),
            color: $('#color').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/bookmark',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('북마크가 저장되었습니다.');
            window.location.href = '/' + prevSectionId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    addOpenCount: function (e) {
        let id = e.target.parentNode.getAttribute("data-bookmarkId");
        $.ajax({
            type: 'PUT',
            url: '/api/v1/bookmark/addcount/' + id,
            dataType: 'json'
        }).done(function () {
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

app.init();

console.log("app.js loaded");