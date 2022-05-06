$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:8085/api/books/by-tag',
        type: 'GET',
        dataType: 'json',
        success: function(data) {

            let arr = [];
            const set = new Set();
            let setElements = 0;
            let avg = 0;

            jQuery.each(data, function (key, value) {
                arr[value.id - 1] = parseInt(value.tagId);

            });

            arr.sort();

            let grouped = arr.reduce(function(r, i) {
                if (typeof r.last === 'undefined' || r.last !== i) {
                    r.last = i;
                    r.arr.push([]);
                }
                r.arr[r.arr.length - 1].push(i);
                return r;
            }, {arr: []}).arr;

            grouped.sort(function (a, b) {
                return b.length - a.length;
            });

            const n = 1;
            const results = new Array(Math.ceil(grouped.length / n))
                .fill()
                .map(_ => grouped.splice(0, n))

            results.forEach(function (value) {
                    console.log(value[0]);
                    set.add(value[0].length)
            });

            set.forEach(function (value) {
                setElements += value;
            });

            avg = setElements / set.size;

            results.forEach(function (value) {

                if( value[0].length < (avg / 2) && value[0].length !== undefined ){
                    $("#" + (value[0][0])).attr("class", "Tag Tag_xs");
                }

                if( avg / 2 < value[0].length < avg && value[0].length !== undefined ){
                    $("#" + (value[0][0])).attr("class", "Tag Tag_sm");
                }

                if ( avg < value[0].length < (avg * 1.5) && value[0].length !== undefined ){
                    $("#" + (value[0][0])).attr("class", "Tag Tag_md");
                }

                if ( (avg * 1.5) < value[0].length && value[0].length !== undefined ){
                    $("#" + (value[0][0])).attr("class", "Tag Tag_lg");
                }

            });

        }})
    });