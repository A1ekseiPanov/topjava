const mealAjaxUrl = "profile/meals/";

const ctx = {
    ajaxUrl: mealAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

function filter() {
    let formId = $("#dataTimeFilter")
    $.ajax({
        url: mealAjaxUrl + filter,
        type: "GET",
        data: {
            startDate: formId.find("#startDate"),
            endDate: formId.find("#endDate"),
            startTime: formId.find("#startTime"),
            endTime: formId.find("#endTime")
        }
    }).done(function () {
        updateTable();
        successNoty("Filtered")
    })
}