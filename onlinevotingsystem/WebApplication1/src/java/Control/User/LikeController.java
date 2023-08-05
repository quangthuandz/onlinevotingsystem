package Control.User;

import Model.Account;
import Model.CommentPost;
import Model.Like;
import Model.Thread;
import Model.Notifications;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //For like and dislike
        Like l = new Like();
        //get accountId and threadId from Thread
        // Get userid
        HttpSession hs = request.getSession();
        Account a = (Account) hs.getAttribute("account");
        String threadId = "";
        if (request.getParameter("threadId") != null)
        {
            threadId = request.getParameter("threadId");
        }
        String commentId = "";
        if (request.getParameter("commentId") != null)
        {
            commentId = request.getParameter("commentId");
        }
        int accountId = a.getId();
        Thread t = new Thread();
        CommentPost cmt = new CommentPost();
        Notifications n = new Notifications();
        t = t.getThreadById(Integer.parseInt(threadId));
        cmt.GetAccountIdByCommentId(commentId);

        String action = request.getParameter("action");

        if (action != null && ((action.equals("likeCmt") || action.equals("dislikeCmt"))))
        {
            l.updateLikeForComment(action, accountId, commentId, threadId);
            if (action.equals("likeCmt"))
            {
                if (cmt.getAccountId() != accountId)
                {
                    n.AddCommentRelatedNotification(cmt.getAccountId(), action, Integer.parseInt(threadId), Integer.parseInt(commentId), accountId);
                }
            }
            else
            {
                n.DeleteCommentRelatedNotification(Integer.parseInt(threadId), Integer.parseInt(commentId), action);
            }
        }
        else if (action != null && ((action.equals("unlikeCmt") || action.equals("undislikeCmt"))))
        {
            l.updateCancelLikeForComment(accountId, commentId);
            if (action.equals("unlikeCmt"))
            {
                n.DeleteCommentRelatedNotification(Integer.parseInt(threadId), Integer.parseInt(commentId), action);
            }
        }
        else if (action != null && ((action.equals("like") || action.equals("dislike"))))
        {
            l.updateLikeForThread(action, accountId, threadId);

            if (action.equals("like") && (t.getAccountId() != accountId))
            {
                if (t.getAccountId() != accountId)
                {
                    n.AddLikeThreadNotification(t.getAccountId(), action, Integer.parseInt(threadId), accountId);
                }
            }
            else
            {
                n.DeleteThreadLikeNotification(Integer.parseInt(threadId), accountId);
            }
        }
        else
        {
            l.updateCancelLikeForThread(accountId, threadId);
            n.DeleteThreadLikeNotification(Integer.parseInt(threadId), accountId);

        }

        int likeCount = 0;
        int dislikeCount = 0;
        if (action != null && ((action.equals("likeCmt") || action.equals("dislikeCmt") || action.equals("unlikeCmt") || action.equals("undislikeCmt"))))
        {
            likeCount = cmt.getLikeByCommentId(commentId);
            dislikeCount = cmt.getDislikeByCommentId(commentId);
        }
        else
        {
            likeCount = t.getLikeByThreadId(threadId);
            dislikeCount = t.getDislikeByThreadId(threadId);
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("{\"likes\": " + likeCount + ", \"dislikes\": " + dislikeCount + "}");
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    }

}
