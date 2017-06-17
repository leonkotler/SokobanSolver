package searcher.searcher;

import searcher.searchable.Searchable;
import searcher.searchable.Solution;

public interface Searcher<T> {

    Solution search(Searchable<T> s);

}
