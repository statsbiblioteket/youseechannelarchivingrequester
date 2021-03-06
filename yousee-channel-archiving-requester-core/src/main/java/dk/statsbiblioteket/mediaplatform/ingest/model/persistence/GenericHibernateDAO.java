/* $Id$
 * $Revision$
 * $Date$
 * $Author$
 *
 *
 */
package dk.statsbiblioteket.mediaplatform.ingest.model.persistence;

import dk.statsbiblioteket.generic.utils.GenericDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public class GenericHibernateDAO<T, PK extends Serializable> implements GenericDAO<T, PK> {

        private HibernateUtilIF util;
        private Class<T> type;

        public GenericHibernateDAO(Class<T> type, HibernateUtilIF util) {
            this.type = type;
            this.util=util;
        }

        public PK create(T o) {
            Session sess = null;
            PK key;
            try {
                sess = getSession();
                sess.beginTransaction();
                key = (PK) sess.save(o);
                sess.getTransaction().commit();
            } finally {
                if (sess != null) {
                    sess.close();
                }
            }
            return key;
        }

        public T read(PK id) {
            Session sess = null;
            try {
                sess = getSession();
                //sess.beginTransaction();
                T result =  (T) sess.get(type, id);
                //sess.getTransaction().commit();
                return result;
            } finally {
                if (sess != null) {
                    sess.close();
                }
            }
        }

        public void update(T o) {
            Session sess = null;
            try {
                sess = getSession();
                sess.beginTransaction();
                sess.update(o);
                sess.getTransaction().commit();
            } finally {
                if (sess != null) {
                    sess.close();
                }
            }
        }

        public void delete(T o) {
            Session sess = null;
            try {
                sess = getSession();
                sess.beginTransaction();
                sess.delete(o);
                sess.getTransaction().commit();
            } finally {
                if (sess != null) {
                    sess.close();
                }
            }
        }

        protected Session getSession() {
            return util.getSession();
        }

        public void flush() {
            getSession().flush();
        }

        /**
             * Use this inside subclasses as a convenience method.
             */
            @SuppressWarnings("unchecked")
            protected List<T> findByCriteria(Criterion... criterion) {
                Session sess = null;
                try {
                    sess = getSession();
                    Criteria crit = sess.createCriteria(type);
                    for (Criterion c : criterion) {
                        crit.add(c);
                    }
                    return crit.list();
                } finally {
                    if (sess != null) {
                        sess.close();
                    }
                }
            }


    }

