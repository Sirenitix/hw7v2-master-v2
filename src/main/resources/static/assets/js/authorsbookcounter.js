$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8085/api/books/allbooks',

        type: 'GET',
        dataType: 'json',
        success: function(data) {

            var results = new RegExp('(?:\/)[0-9]+').exec(window.location.href);
            let authorId = results[0].replace("/", "");

            let index;
            let arr = [];
            let index_counter = 0;

            jQuery.each(data, function (key, value) {
                if(parseInt(value.authors[0].id) === parseInt(authorId)){
                    const index = index_counter++;
                    arr[index] = parseInt(value.id);
                    }
                });

            console.log(arr.length);
            $("#" + 999).html("(" + arr.length + ")");
        }})
});