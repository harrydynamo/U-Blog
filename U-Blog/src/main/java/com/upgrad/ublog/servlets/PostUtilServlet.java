package com.upgrad.ublog.servlets;

/**
 * TODO: 7.6. Modify the class definition to make it a Servlet class (through inheritance).
 * TODO: 7.7. Provide an attribute of type PostService. Override init() method to instantiate
 *  this attribute using the ServiceFactory class.
 * TODO: 7.8. Override doPost() method from the base Class.
 * TODO: 7.9. Check if the user is logged in or not. If not, then redirect them to the
 *  login page. (Hint: Make use of session object)
 * TODO: 7.10. If the request is coming from the Search.jsp page, then do the following:
 *  1. Retrieve the email id from the request object.
 *  2. Validate the email id using the EmailValidator class. If the email is not valid,
 *   then redirect the user to the Search.jsp page with the error message that is stored
 *   in the EmailNotValidException. This error message should be displayed on the Search.jsp page.
 *   (Hint: Don't forget to add the return message after you redirect the user.)
 *  3. If the email id is valid, then fetch all the posts corresponding the email id retrieved from the
 *   request object using the PostService.
 *  4. If no post exists corresponding to the email id, then throw new PostNotFoundException with a message
 *   "Sorry no posts exists for this email id". This exception should be caught and the message should be
 *   passed to the Search.jsp page, where it gets displayed to the user.
 *  5. If posts exist corresponding to the email id, then load the List of PostDTO objects into request
 *   object as an attribute and redirect to the Search.jsp page.
 *  6. If some exception was thrown during the searching of the post, such as PostNotFoundException, handle
 *   all those exceptions, pass the message stored in the exceptions to the Search.jsp page as an attribute
 *   to the request object.
 */

/**
 * TODO: 7.19. If the request is coming from the Delete.jsp page, then do the following:
 *  1. Retrieve the post id from the request object and email id from the session object.
 *  2. Try to delete the post using the deletePost() method of the PostService interface and pass the
 *   post id and the email id.
 *  3. If the deletePost() method return true means post was deleted successfully. In such a scenario,
 *   load the "Post deleted successfully!" message into the request object as an attribute and redirect to
 *   the Delete.jsp page, where this message gets displayed to the user.
 *  4. If the deletePost() method return false means post exists but it was not created by the same user who is
 *   currently logged in. In such a scenario, load the "You are not authorised to delete this post" message
 *   into the request object as an attribute and redirect to the Delete.jsp page, where this message gets
 *   displayed to the user.
 *  5. If some exception was thrown during the deletion of the post, such as PostNotFoundException, handle
 *   all those exceptions, pass the message stored in the exceptions to the Delete.jsp page as an attribute
 *   to the request object.
 */

import com.upgrad.ublog.dto.PostDTO;
import com.upgrad.ublog.exceptions.EmailNotValidException;
import com.upgrad.ublog.exceptions.PostNotFoundException;
import com.upgrad.ublog.services.PostService;
import com.upgrad.ublog.services.ServiceFactory;
import com.upgrad.ublog.utils.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * TODO: 7.29. If the request is coming from the Filter.jsp page, then do the following:
 *  1. Retrieve the tag from the request object.
 *  2. Fetch all the posts corresponding to the tag using the getPostsByTag() method of the PostService.
 *  3. If no post exists corresponding to the tag, then throw new PostNotFoundException with a message
 *   "Sorry no posts exists for this tag. This exception should be caught and the message should be
 *   passed to the Filter.jsp page, where it gets displayed to the user.
 *  4. If posts exist corresponding to the tag, then load the list of PostDTO objects into request
 *   object as an attribute and redirect to the Filter.jsp page.
 *  5. If some exception was thrown during the filtering of the post, such as PostNotFoundException, handle
 *   all those exceptions, pass the message stored in the exceptions to the Filter.jsp page as an attribute
 *   to the request object.
 */

@WebServlet("/ublog/post/util")
public class PostUtilServlet extends HttpServlet {
    PostService postService;
    String email;
    @Override
    public void init() throws ServletException {
        postService = ServiceFactory.getPostServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("email") == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        String search = req.getParameter("search");
        String delete = req.getParameter("delete");
        String filter = req.getParameter("filter");

        if (delete != null) {
            deletePost(req, resp);
            return;
        }
        if (search != null){
            searchPost(req, resp);
            return;
        }

        if (filter != null) {
            filterPost(req, resp);
            return;
        }
        System.out.println("Nothing Selected ");
        req.getRequestDispatcher("/Home.jsp").forward(req, resp);
    }

    private void searchPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email = req.getParameter("email");
        try {
            EmailValidator.isValidEmail(email);
            List<PostDTO> posts = postService.getPostsByEmail(email);
            if (posts.size() == 0)
                throw new PostNotFoundException("Sorry no posts exists for this email id");
            req.setAttribute("posts", posts);
        } catch (Exception | EmailNotValidException e) {
            req.setAttribute("message", e.getMessage());
        }
        req.getRequestDispatcher("/ublog/Search.jsp").forward(req, resp);
    }

    private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email = (String) req.getSession().getAttribute("email");
        try {
            int postId = Integer.parseInt(req.getParameter("postId"));
            boolean result = postService.deletePost(postId, email);
            if (!result) {
                req.setAttribute("message", "You are not authorised to delete this post");
            } else {
                req.setAttribute("message", "Post deleted successfully!");
            }

        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
        }
        req.getRequestDispatcher("/ublog/Delete.jsp").forward(req, resp);
    }

    private void filterPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = req.getParameter("tag");
        try {
            List<PostDTO> posts = postService.getPostsByTag(tag);
            if (posts.size() == 0)
                throw new PostNotFoundException("Sorry no posts exists for this tag");
            req.setAttribute("posts", posts);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", e.getMessage());
        }

        req.getRequestDispatcher("/ublog/Filter.jsp").forward(req, resp);
    }
}
