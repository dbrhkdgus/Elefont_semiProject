<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- Footer Start -->
    <footer class="footer-section">
        <div class="container">
            <div class="row">

                <div class="col-md-4 text-left">
                    <p><span><a href="<%=request.getContextPath() %>/about" class="smoth-scroll">About Me</a></span> | <span><a href="<%=request.getContextPath() %>/shop"
                                class="smoth-scroll">Shop</a></span></p>
                </div>

                <div class="col-md-4 text-center">
                    <p>© Copyright 2021 팀명.</p>
                </div>

                <div class="col-md-4 uipasta-credit text-right">
                    <p>Designed By <span class="footer-teamName">팀명</span></p>
                </div>

            </div>
        </div>
    </footer>
    <!-- Footer End -->


    <!-- Back to Top Start -->
    <a href="#" class="scroll-to-top"><i class="fas fa-angle-double-up"></i></i></a>
    <!-- Back to Top End -->


    <!-- All Javascript Plugins  -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/plugin.js"></script>
    <script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=AIzaSyC0HAKwKinpoFKNGUwRBgkrKhF-sIqFUNA"></script>

    <!-- Main Javascript File  -->
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/scripts.js"></script>


</body>

</html>