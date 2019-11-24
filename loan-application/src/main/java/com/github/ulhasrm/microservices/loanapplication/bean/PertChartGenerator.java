package com.github.ulhasrm.microservices.loanapplication.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.ulhasrm.microservices.loanapplication.entity.Status;
import com.github.ulhasrm.microservices.loanapplication.entity.WorkFlowTransition;
import com.github.ulhasrm.microservices.loanapplication.service.StatusDaoService;

public class PertChartGenerator
{
    @Autowired
    StatusDaoService statusService;

    private String currentStatusColor = "#8bc34a";
    private String defaultColor = "#000000";

    public PertChartData generateChart( final List<WorkFlowTransition> transitions, final Status currentStatus )
    {
        final Links links = new Links();
        final StringBuilder sb = new StringBuilder();
        sb.append( "\ngraph TB" );

        final Set<Status> usedStatus = new HashSet<>();
        for( WorkFlowTransition transition : transitions )
        {
            final Status from = transition.getFrom();
            final Status to = transition.getTo();

            if( !usedStatus.contains( from ) )
            {
                final String color = currentStatus.getId() == from.getId() ? currentStatusColor : defaultColor;
                StatusGroup statusNode = new StatusGroup( from, color );
                statusNode.process( sb );
                usedStatus.add( from );
            }

            if( !usedStatus.contains( to ) )
            {
                final String color = currentStatus.getId() == to.getId() ? currentStatusColor : defaultColor;
                StatusGroup statusNode = new StatusGroup( to, color );
                statusNode.process( sb );
                usedStatus.add( to );
            }

            links.addLink( String.valueOf( from.getId() ), String.valueOf( to.getId() ) );
        }

        PertChartData data;
        try
        {
            links.process( sb );
            for( final Status status : usedStatus )
            {
                CompletedStyle style = new CompletedStyle( status.getId(), status.getName() );
                style.process( sb );
            }
            final String result = sb.toString();
            data = new PertChartData( true, result );
        }
        catch( Exception e )
        {
            data = new PertChartData( false, "" );
        }

        return data;
    }

    class Links
    {
        List<LinkNode> nodes = new ArrayList<>();

        public void Link()
        {
        }

        public void addLink( final String f, final String t )
        {
            nodes.add( new LinkNode( f, t ) );
        }

        public void addLink( final String f, final String t, final String style )
        {
            nodes.add( new LinkNode( f, t, style ) );
        }

        public void process( final StringBuilder sb )
        {
            markDuplicates();
            for( final LinkNode node : nodes )
            {
                node.process( sb );
            }
        }

        private void markDuplicates()
        {
            final Set<LinkNode> unique = new HashSet<>();
            for( final LinkNode node : nodes )
            {
                if( unique.contains( node ) )
                {
                    node.process = false;
                }
                else
                {
                    unique.add( node );
                }
            }
        }
    }

    class LinkNode
    {
        String from;
        String to;
        String style;
        boolean process = true;

        public LinkNode( final String f, final String t )
        {
            from = f;
            to = t;
            style = "REGULAR";
        }

        public LinkNode( final String f, final String t, final String s )
        {
            from = f;
            to = t;
            style = s;
        }

        public void process( final StringBuilder sb )
        {
            if( !process )
            {
                return;
            }

            sb.append( "\n" ).append( from + " --> " + to );
        }

        @Override
        public boolean equals( final Object obj )
        {
            if( obj instanceof LinkNode )
            {
                final LinkNode that = (LinkNode)obj;
                if( this.from.equals( that.from ) && this.to.equals( that.to ) )
                {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + from.hashCode();
            result = prime * result + to.hashCode();
            return result;
        }
    }

    interface ChartStyle
    {
        public void process( StringBuilder sb );
    }

    class CompletedStyle implements ChartStyle
    {
        final Long statusId;
        String fill = "#ECECFF";
        final static String stroke = "#CCCCFF";
        final static String strokeWidth = "2px";

        public CompletedStyle( final Long statusId, final String statusName )
        {
            this.statusId = statusId;
        }

        @Override
        public void process( final StringBuilder sb )
        {
            final String style = "style " + statusId + " fill:" + fill + ", stroke:" + stroke + ";";
            sb.append( "\n" );
            sb.append( style );
        }
    }

    class StatusGroup
    {
        final Status status;
        final String color;

        public StatusGroup( final Status status, final String color )
        {
            this.status = status;
            this.color = color;
        }

        public void process( final StringBuilder sb )
        {
            final String statusId = String.valueOf( status.getId() );
            final String name = status.getName();
            sb.append( "\n" ).append( statusId );
            sb.append( "(\"<span style='color:" + color + "'>" ).append( name ).append( "</span>\")" );
        }
    }
}
