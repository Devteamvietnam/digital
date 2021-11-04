package com.devteam.tutorial.algorithms.sort;

public class QuickSortT {
  private Integer[] arrayNumbers;
  private Integer   number;

  public void sort(Integer[] values) {
    // check for empty or null array
    if (values == null || values.length == 0) {
      return;
    } else {
      this.arrayNumbers = values;
      number = values.length;
      quickSort(0, number - 1);
    }
  }

  void quickSort(int i, int j) {
    int low = i, high = j;
    // get the element from the middle of the list
    int arrays = arrayNumbers[low + (high - low) / 2];
    // Divide into two lists
    while (i <= j) {
      // Nếu giá trị hiện tại từ danh sách bên trái nhỏ hơn giá trị arrays
      // phần tử sau đó lấy phần tử tiếp theo từ danh sách bên trái
      while (arrayNumbers[i] < arrays) {
        i++;
      }
      // Nếu giá trị hiện tại từ danh sách bên phải lớn hơn arrays
      // phần tử sau đó lấy phần tử tiếp theo từ danh sách bên phải
      while (arrayNumbers[j] > arrays) {
        j--;
      }
      // Nếu đã tìm thấy một giá trị trong danh sách bên trái lớn hơn phần tử arrays
      // Nếu đã tìm thấy giá trị trong danh sách bên phải nhỏ hơn phần tử arrays
      // thì trao đổi giá trị.
      // Khi hoàn thành, chúng có thể tăng i và j
      if (i <= j) {
        exchange(i, j);
        i++;
        j--;
      }
    }
    if (low < j)
    quickSort(low, j);
    if (i < high)
    quickSort(i, high);
  }

  private void exchange(int i, int j) {
    int temp = arrayNumbers[i];
    arrayNumbers[i] = arrayNumbers[j];
    arrayNumbers[j] = temp;
  }

}
