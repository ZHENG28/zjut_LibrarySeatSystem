let layoutStr = "";
let sc, mapArr;

// $(document).ready(function () {
function seatLayout() {
    let firstSeatLabel = 1;
    let $cart = $('#selected-seats'),
        $counter = $('#counter'),
        $total = $('#total');
    sc = $('#seat-map1').seatCharts({
        map: mapArr,
        // map: [
        //     'e_ff_e',
        //     'effffe',
        //     'ffffff',
        //     'f_ff_f',
        //     'e_ff_e',
        //     'e_ff_e',
        //     'f_ff_f',
        //     'ffffff',
        //     'efffff',
        //     'e_ff_e',
        // ],
        seats: {
            f: {
                price: 0,
                classes: 'first-class', //your custom CSS class
                category: '4人桌'
            },
            e: {
                price: 0,
                classes: 'second-class', //your custom CSS class
                category: '6人桌'
            }

        },
        naming: {
            top: false,
            getLabel: function (character, row, column) {
                return firstSeatLabel++;
            },
        },
        legend: {
            node: $('#legend'),
            items: [
                ['f', 'available', '4人桌'],
                ['e', 'available', '6人桌'],
                ['f', 'unavailable', '已满的4人桌'],
                ['e', 'unavailable', '已满的6人座']
            ]
        },
        click: function () {
            // 座位可以选中
            if (this.status() == 'available') {
                // 已预约一个座位，选择失败
                if (parseInt($counter.text()) >= 1) {
                    alert("一个人一次最多预约一个座位，请先取消选中的座位！");
                    return 'available';
                }
                // 添加座位
                $('<li>' + this.data().category + this.settings.label + '号桌' + '：<br/><a href="#" class="cancel-cart-item">[删除]</a></li>')
                    .attr('id', 'cart-item-' + this.settings.id)
                    .attr('name', 'deskId').attr('value', this.settings.label)
                    .data('seatId', this.settings.id)
                    .appendTo($cart);
                $counter.text(sc.find('selected').length + 1);
                // $total.text(recalculateTotal(sc)+this.data().price);
                return 'selected';
            } else if (this.status() == 'selected') {
                //update the counter
                $counter.text(sc.find('selected').length - 1);
                //and total
                $total.text(recalculateTotal(sc) - this.data().price);

                //remove the item from our cart
                $('#cart-item-' + this.settings.id).remove();

                //seat has been vacated
                return 'available';
            } else if (this.status() == 'unavailable') {
                //seat has been already booked
                return 'unavailable';
            } else {
                return this.style();
            }
        }
    });

    //this will handle "[cancel]" link clicks
    $('#selected-seats').on('click', '.cancel-cart-item', function () {
        //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
        sc.get($(this).parents('li:first').data('seatId')).click();
    });

    function recalculateTotal(sc) {
        let total = 0;
        //basically find every selected seat and sum its price
        sc.find('selected').each(function () {
            total += this.data().price;
        });

        return total;
    }

    //let's pretend some seats have already been booked
    sc.get(['1_2', '4_1', '7_1', '7_3']).status('unavailable');

    /*setInterval(function() {//自动刷新
        $.ajax({
            type     : 'get',
            url      : '/bookings/get/100',
            dataType : 'json',
            success  : function(response) {
                //iterate through all bookings for our event
                $.each(response.bookings, function(index, booking) {
                    //find seat by id and set its status to unavailable
                    sc.status(booking.seat_id, 'unavailable');
                });
            }
        });
}, 10000); // 10S */


}

// )