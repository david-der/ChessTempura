<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/header_incl.jsp" %>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>${message1}</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="chess-board">
                    <tr>
                    	<td id="8" class="border" align="center">8</td>
                    	
                        <td id="a8" class="light-square">
                            <img src="<c:url value='/resources/images/bR.png'/>" class="bRook">
                        </td>
                        <td id="b8" class="dark-square">
                            <img src="<c:url value='/resources/images/bN.png'/>" class="bKnight">
                        </td>
                        <td id="c8" class="light-square">
                            <img src="<c:url value='/resources/images/bB.png'/>" class="bBishop">
                        </td>
                        <td id="d8" class="dark-square">
                            <img src="<c:url value='/resources/images/bQ.png'/>" class="bQueen">
                        </td>
                        <td id="e8" class="light-square">
                            <img src="<c:url value='/resources/images/bK.png'/>" class="bKing">
                        </td>
                        <td id="f8" class="dark-square">
                            <img src="<c:url value='/resources/images/bB.png'/>" class="bBishop">
                        </td>
                        <td id="g8" class="light-square">
                            <img src="<c:url value='/resources/images/bN.png'/>" class="bKnight">
                        </td>
                        <td id="h8" class="dark-square">
                            <img src="<c:url value='/resources/images/bR.png'/>" class="bRook">
                        </td>
                    </tr>
                    <tr>
                    	<td id="7" class="border" align="center">7</td>
                    	
                        <td id="a7" class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="b7" class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="c7" class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="d7" class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="e7" class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="f7" class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="g7" class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                        <td id="h7" class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="bPawn">
                        </td>
                    </tr>
                    <tr>
                    	<td id="6" class="border" align="center">6</td>
                        <td id="a6" class="light-square">&nbsp;</td>
                        <td id="b6" class="dark-square">&nbsp;</td>
                        <td id="c6" class="light-square">&nbsp;</td>
                        <td id="d6" class="dark-square">&nbsp;</td>
                        <td id="e6" class="light-square">&nbsp;</td>
                        <td id="f6" class="dark-square">&nbsp;</td>
                        <td id="g6" class="light-square">&nbsp;</td>
                        <td id="h6" class="dark-square">&nbsp;</td>
                    </tr>
                    <tr>
                    	<td id="5" class="border" align="center">5</td>
                        <td id="a5" class="dark-square">&nbsp;</td>
                        <td id="b5" class="light-square">&nbsp;</td>
                        <td id="c5" class="dark-square">&nbsp;</td>
                        <td id="d5" class="light-square">&nbsp;</td>
                        <td id="e5" class="dark-square">&nbsp;</td>
                        <td id="f5" class="light-square">&nbsp;</td>
                        <td id="g5" class="dark-square">&nbsp;</td>
                        <td id="h5" class="light-square">&nbsp;</td>
                    </tr>
                    <tr>
                    	<td id="4" class="border" align="center">4</td>
                        <td id="a4" class="light-square">&nbsp;</td>
                        <td id="b4" class="dark-square">&nbsp;</td>
                        <td id="c4" class="light-square">&nbsp;</td>
                        <td id="d4" class="dark-square">&nbsp;</td>
                        <td id="e4" class="light-square">&nbsp;</td>
                        <td id="f4" class="dark-square">&nbsp;</td>
                        <td id="g4" class="light-square">&nbsp;</td>
                        <td id="h4" class="dark-square">&nbsp;</td>
                    </tr>
                    <tr>
                        <td id="3" class="border" align="center">3</td>
                        <td id="a3" class="dark-square">&nbsp;</td>
                        <td id="b3" class="light-square">&nbsp;</td>
                        <td id="c3" class="dark-square">&nbsp;</td>
                        <td id="d3" class="light-square">&nbsp;</td>
                        <td id="e3" class="dark-square">&nbsp;</td>
                        <td id="f3" class="light-square">&nbsp;</td>
                        <td id="g3" class="dark-square">&nbsp;</td>
                        <td id="h3" class="light-square">&nbsp;</td>
                    </tr>
                    <tr>
                    	<td id="2" class="border" align="center">2</td>						
                   
                        <td id="a2" class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="b2" class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="c2" class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="d2" class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="e2" class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="f2" class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="g2" class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                        <td id="h2" class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="wPawn">
                        </td>
                    </tr>
                    <tr>
						<td id="1" class="border" align="center">1</td>
												
                        <td id="a1" class="dark-square">
                            <img src="<c:url value='/resources/images/wR.png'/>" class="wRook">
                        </td>
                        <td id="b1" class="light-square">
                            <img src="<c:url value='/resources/images/wN.png'/>" class="wKnight">
                        </td>
                        <td id="c1" class="dark-square">
                            <img src="<c:url value='/resources/images/wB.png'/>" class="wBishop">
                        </td>
                        <td id="d1" class="light-square">
                            <img src="<c:url value='/resources/images/wQ.png'/>" class="wQueen">
                        </td>
                        <td id="e1" class="dark-square">
                            <img src="<c:url value='/resources/images/wK.png'/>" class="wKing">
                        </td>
                        <td id="f1" class="light-square">
                            <img src="<c:url value='/resources/images/wB.png'/>" class="wBishop">
                        </td>
                        <td id="g1" class="dark-square">
                            <img src="<c:url value='/resources/images/wN.png'/>" class="wKnight">
                        </td>
                        <td id="h1" class="light-square">
                            <img src="<c:url value='/resources/images/wR.png'/>" class="wRook">
                        </td>
                    </tr>
                    <tr>
                       	<td id="empty-corner" class="border" align="center">&nbsp;</td>
                    	<td id="a" class="border" align="center">a</td>
                    	<td id="b" class="border" align="center">b</td>
                    	<td id="c" class="border" align="center">c</td>
                    	<td id="d" class="border" align="center">d</td>
                    	<td id="e" class="border" align="center">e</td>
                    	<td id="f" class="border" align="center">f</td>
                    	<td id="g" class="border" align="center">g</td>
                    	<td id="h" class="border" align="center">h</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        var clickCount = 0;
        var isPieceHighlighted = false;
        var highlightedPiece;
        var capturedPiece;
        var start = null; //id, e2
        var end = null;   //id, e4
		var piece = null; //class, wPawn
        //var legalMove = false;
        //var square = null;

        initializeBoard();

        $('#chess-board td').click(function() {
            if (isPieceHighlighted)
                clickCount++;

            if (clickCount <= 1) {
                start = $(this).attr("id");
            } else {
                square = this;
                end = $(this).attr("id");
                sendMoveToServer(start, end, piece, this);
                console.log('Ok5, just clicked a piece and a square');
            }
        });

        $('#chess-board img').click(function() {
            // Unhighlight all the images
            $('#chess-board img').removeClass('highlighted');

            if(this == highlightedPiece) {
                resetVars();
                console.log('same piece');
            }
            else if (isPieceHighlighted) {
                console.log('capturing');
                //sendCaptureToServer(start, end, piece, this);
                //var this_src = $(this).attr("src");
                //= highlightedPiece.attr("src");
            }
            else {
                //else, just highlight the newly selected image
                $(this).addClass('highlighted');
                piece = $(this).attr("class");
                isPieceHighlighted = true;
                highlightedPiece = this;
                console.log('different piece');
            }
        });

        function initializeBoard() {
            $.ajax({
                type: "POST",
                url: "/initializeBoard.htm",
                success: function(response){
                    console.log("initializeBoard");
                },
                error: function(e){
                    alert('Error in initializeBoard');
                }
            });
        }

        function sendMoveToServer(start, end, p, sq) {
            $.ajax({
                type: "POST",
                url: "/makeMove.htm",
                data: "startSquare=" + start + "&endSquare=" + end + "&piece=" + p + "&square=" + sq,
                success: function(response){
                    // we have the response
                    console.log("YES: " + response);
                    if(response == "illegal") {
                        console.log('if statement, illegal ' + response);
                        //legal_move = false;
                        $('#chess-board img').removeClass('highlighted');
                        resetVars();
                    }
                    else {
                        console.log('if statement, legal ' + response);
                        //legal_move = true;
                        $(sq).append(highlightedPiece);
                        $('#chess-board img').removeClass('highlighted');
                        resetVars();
                        isCheck();  //after a successful move, see if the king is in check
                    }
                },
                error: function(e){
                    alert('Error in send move to server: ' + start + end + p);
                }
            });
            //return legal_move;
        }

        function sendCaptureToServer(start, end, p, capturedPiece) {
            $.ajax({
                type: "POST",
                url: "/makeMove.htm",
                data: "startSquare=" + start + "&endSquare=" + end + "&piece=" + p + "&captured_piece=" + capturedPiece,
                success: function(response){

                },
                error: function(e){
                    alert('Error in send capture to server.');
                }
            });
            //return legal_move;
        }

        function isCheck() {
            $.ajax({
                type: "POST",
                url: "/isCheck.htm",
                success: function(response){
                    //console.log("success in isCheck");
                },
                error: function(e){
                    alert('Error in isCheck');
                }
            });
        }

        function resetVars() {
            clickCount = 0;
            isPieceHighlighted = false;
            highlightedPiece = null;
            start = null;
            end = null;
            piece = null;
            $('#chess-board img').removeClass('highlighted');
        }
        
        <%--function makeTableHTML(myArray) {
            var result = "<table border=1>";
            for(var i=0; i<myArray.length; i++) {
                result += "<tr>";
                for(var j=0; j<myArray[i].length; j++){
                    result += "<td>"+myArray[i][j]+"</td>";
                }
                result += "</tr>";
            }
            result += "</table>";

            return result;
        }
        --%>
        
    </script>
</body>
</html>