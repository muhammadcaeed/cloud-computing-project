$(document).ready(function () {
    $('#search-item').autocomplete({
        paramName: 'query',
        transformResult: function(response) {
            return {
                suggestions: $.map(response.myData, function(dataItem) {
                    return { value: dataItem.valueField, data: dataItem.dataField };
                })
            };
        }
    })
});