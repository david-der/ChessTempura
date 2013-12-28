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
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bR.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bN.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bB.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bQ.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bK.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bB.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bN.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bR.png'/>" class="img-piece">
                        </td>
                    </tr>
                    <tr>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/bP.png'/>" class="img-piece">
                        </td>
                    </tr>
                    <tr>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                        <td class="dark-square">&nbsp;</td>
                        <td class="light-square">&nbsp;</td>
                    </tr>
                    <tr>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wP.png'/>" class="img-piece">
                        </td>
                    </tr>
                    <tr>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wR.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wN.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wB.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wQ.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wK.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wB.png'/>" class="img-piece">
                        </td>
                        <td class="dark-square">
                            <img src="<c:url value='/resources/images/wN.png'/>" class="img-piece">
                        </td>
                        <td class="light-square">
                            <img src="<c:url value='/resources/images/wR.png'/>" class="img-piece">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script type="text/javascript">

        var clickCount = 0;
        var isPieceHighlighted = false;
        var isWhitesTurn = true;
        var highlightedPiece;

        $('#chess-board td').click(function() {
            if (isPieceHighlighted)
                clickCount++;

            if (clickCount <= 1) {
                return false;
            } else {
                var isLegal = sendMoveToServer();
                if (! isLegal){
                    // rollback the move that just was attempted
                }
                $(this).append(highlightedPiece);
                console.log('Ok, just clicked a piece and a square');
            }
        });

        $('#chess-board img').click(function() {
            // Unhighlight all the images
            $('#chess-board img').removeClass('highlighted');

            if (! (this == highlightedPiece)) {
                // Highlight the newly selected image
                $(this).addClass('highlighted');
                isPieceHighlighted = true;
                highlightedPiece = this;
                console.log('different piece');
            } else {
                highlightedPiece = null;
                clickCount = 0;
                isPieceHighlighted = false;
                console.log('same piece');
            }
        });

        function sendMoveToServer() {
            $.ajax({
                type: "POST",
                url: "/makeMove.htm",
                data: "test=Bryan",
                success: function(response){
                    // we have the response
                    console.log("YES");
                },
                error: function(e){
                    alert('Error: ' + e);
                }
            });
        }
    </script>
</body>
</html>