$(function () {
    const preDefinedLang = {
        'en-US': {
            path: 'en',
            mods: {
                sLengthMenu: "Display ENGLISH _MENU_ records per page"
            }
        },
        'en': {
            path: 'en-GB',
            mods: {
                sLengthMenu: "Display ENGLISH _MENU_ records per page"
            }
        },
        'vi': {
            path: 'vi',
            mods: {
                sLengthMenu: "Display VIETNAMESE _MENU_ records per page"
            }
        }
    };

    function getDataTableLanguage() {
        var lang = navigator.language || navigator.userLanguage;
        var result = null;
        var path = '//cdn.datatables.net/plug-ins/1.12.1/i18n/';
        $.ajax({
            async: false,
            url: path + preDefinedLang[lang].path + '.json',
            success: function (obj) {
                result = $.extend({}, obj, preDefinedLang[lang].mods)
            }
        })
        return result
    }

    let table = $('table#clientTableData').DataTable({
        ajax: {
            contentType: 'application/json',
            url: '/api/client/list',
            type: 'POST',
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        serverSide: true,
        autoWidth: false,
        info: true,
        responsive: true,
        language: getDataTableLanguage(),
        select: {
            style: 'multi'
        },
        columns: [
            {
                data: 'id'
            },
            {
                data: 'clientId'
            },
            {
                data: 'clientName'
            },
            {
                data: 'scopes'
            },
            {
                data: 'authorizationGrantTypes'
            },
            {
                data: 'createdDate'
            },
            {
                data: 'lastModifiedDate'
            },
            {
                data: 'actions',
                searchable: false,
                orderable: false,
                render: function (data, _, _, _) {
                    return '<a href="/portal/client/edit/' + data + '"><i class="fas fa-pen-square"></i></a>';
                }
            }
        ]
    });
    $('table#clientTableData tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');
        if (table.rows('.selected').data().length > 0) {
            $('#deleteRows').removeClass("disabled");
        } else {
            $('#deleteRows').addClass("disabled");
        }
    });
    $('button#deleteRows').on('click', function () {
        $('#confirm-delete-box').modal('show');
    })
    $('button#confirmed-delete').on('click', function () {
        let deletedIds = [];
        table.rows('.selected').data().each(function (row) {
            deletedIds.push(row['id']);
        })
        $.ajax({
            contentType: "application/json",
            url: '/api/client/delete',
            type: 'POST',
            data: JSON.stringify(deletedIds),
            success: function (res) {
                toastr.success(res.payload);
                table.ajax.reload();
                $('#deleteRows').addClass("disabled");
                $('#confirm-delete-box').modal('hide');
            },
            error: function (res, _, _) {
                toastr.error(res.responseJSON.payload.message);
                $('#confirm-delete-box').modal('hide');
            }
        })
    })
});